package main.com.bsuir.autoservice.command.document;

import com.google.inject.Inject;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.DocumentInfo;
import main.com.bsuir.autoservice.command.ret.DocumentRet;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.InvalidObjectException;
import java.lang.reflect.Field;
import java.util.List;

public abstract class GenerateDocumentCommand<R extends DocumentInfo> implements ICommand<R, DocumentRet>{

    protected abstract void fillXmlDoc(org.w3c.dom.Document document, org.w3c.dom.Element reportContentElement, R documentInfo) throws Exception;
    protected abstract void fillPdfDoc(Document document, PdfWriter pdfWriter, R documentInfo) throws Exception;
    protected abstract void fillSheet(HSSFWorkbook workbook, HSSFSheet sheet, R documentInfo, int startRowIndex) throws Exception;

    protected abstract String getDocumentTitle();
    protected abstract String getDocumentDescription();
    protected abstract String getDocumentSaveFileName();

    @Inject
    public GenerateDocumentCommand(/*ISession session, */IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.databaseMap = databaseMap;
//        this.session = session;
    }

    @Override
    public DocumentRet execute(R documentInfo) throws CommandException {
        try {
            DocumentRet documentRet = new DocumentRet();
            documentRet.documentStream = new ByteArrayOutputStream();
            switch (documentInfo.format){
                case "xml":
                    return GenerateXml(documentRet, documentInfo);
                case "pdf":
                    return GeneratePdf(documentRet, documentInfo);
                case "xls":
                    return GenerateXls(documentRet, documentInfo);
            }
            throw new InvalidObjectException(null);
        } catch (Exception e) {
            throw new CommandException(e);
        }
    }

    private DocumentRet GenerateXml(DocumentRet documentRet, R documentInfo)
        throws Exception{
        DocumentGenerationInfo documentGenerationInfo = getDocumentGenerationInfo();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        org.w3c.dom.Document document = docBuilder.newDocument();
        org.w3c.dom.Element rootElement = document.createElement("report");
        org.w3c.dom.Element reportName = document.createElement("name");
        reportName.appendChild(document.createTextNode(getDocumentTitle()));
        org.w3c.dom.Element generationTime = document.createElement("generation_time");
        generationTime.appendChild(document.createTextNode(documentGenerationInfo.generationTime.toString()));
        org.w3c.dom.Element generatedBy = document.createElement("author");
        generatedBy.appendChild(document.createTextNode(documentGenerationInfo.generatedBy));
        org.w3c.dom.Element reportDescription = document.createElement("description");
        reportDescription.appendChild(document.createTextNode(getDocumentDescription()));
        org.w3c.dom.Element reportContent = document.createElement("content");
        rootElement.appendChild(reportName);
        rootElement.appendChild(generationTime);
        rootElement.appendChild(generatedBy);
        rootElement.appendChild(reportDescription);
        rootElement.appendChild(reportContent);
        document.appendChild(rootElement);

        fillXmlDoc(document, reportContent, documentInfo);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult streamResult = new StreamResult(documentRet.documentStream);
        transformer.transform(source, streamResult);

        documentRet.contentType = xmlDocumentContentType;
        documentRet.fileName = getDocumentSaveFileName() + xmlDocumentExtension;
        return documentRet;
    }

    private DocumentRet GeneratePdf(DocumentRet documentRet, R documentInfo)
            throws Exception{
        Document document = new Document(PageSize.A4, documentMargin, documentMargin, documentMargin, documentMargin);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, documentRet.documentStream);
        pdfWriter.setPageEvent(new PdfPageEventHelper(){
            @Override
            public void onStartPage(final PdfWriter writer, final Document document){
                renderDocBorder(document, writer);
                writeDocFooter(document, writer);
            }
        });

        document.open();
        addDocTitlePage(document, pdfWriter);
        fillPdfDoc(document, pdfWriter, documentInfo);
        document.close();

        documentRet.contentType = pdfDocumentContentType;
        documentRet.fileName = getDocumentSaveFileName() + pdfDocumentExtension;
        return documentRet;
    }

    private DocumentRet GenerateXls(DocumentRet documentRet, R documentInfo)
            throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        for(int i = 0; i < 100; i++){
            sheet.autoSizeColumn(i);
        }
        int headerRowsCount = writeSheetDocInfo(sheet);
        fillSheet(workbook, sheet, documentInfo, headerRowsCount);
        workbook.write(documentRet.documentStream);

        documentRet.contentType = excelSheetContentType;
        documentRet.fileName = getDocumentSaveFileName() + excelSheetExtension;
        return documentRet;
    }

    protected static void addDocEmptyLine(Document document, int number)
        throws DocumentException{
        Paragraph paragraph = new Paragraph();
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph());
        }
        document.add(paragraph);
    }

    private void addDocTitlePage(Document document, PdfWriter writer)
            throws Exception {
        addDocEmptyLine(document, 10);
        writeDocTitle(document, getDocumentTitle());
        writeSubTitle(document, getDocumentDescription());
        addDocEmptyLine(document, 2);
        document.add(getDocumentGenerationInfoParagraph());

        document.newPage();
    }

    protected void writeDocTitle(Document document, String text)
            throws DocumentException{
        Paragraph titleParagraph = new Paragraph(text, reportTitleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);
        addDocEmptyLine(document, 1);
    }

    protected void writeTitle(Document document, String text)
            throws DocumentException{
        Paragraph titleParagraph = new Paragraph(text, titleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);
        addDocEmptyLine(document, 1);
    }

    protected int writeSheetDocInfo(Sheet sheet) throws Exception{
        DocumentGenerationInfo documentGenerationInfo = getDocumentGenerationInfo();
        int currentRow = 0;
        Row titleRow = sheet.createRow(currentRow);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellType(CellType.STRING);
        titleCell.setCellValue(getDocumentTitle());
        currentRow++;
        Row genTimeRow = sheet.createRow(currentRow);
        Cell genTimeRowNameCell = genTimeRow.createCell(0);
        genTimeRowNameCell.setCellType(CellType.STRING);
        genTimeRowNameCell.setCellValue("Document generation time");
        Cell genTimeRowValueCell = genTimeRow.createCell(1);
        genTimeRowValueCell.setCellType(CellType.STRING);
        genTimeRowValueCell.setCellValue(documentGenerationInfo.generationTime.toString());
        currentRow++;
        Row getByRow = sheet.createRow(currentRow);
        Cell getByRowName = getByRow.createCell(0);
        getByRowName.setCellType(CellType.STRING);
        getByRowName.setCellValue("Document generated by");
        Cell getByRowValue = getByRow.createCell(1);
        getByRowValue.setCellType(CellType.STRING);
        getByRowValue.setCellValue(documentGenerationInfo.generatedBy);
        return currentRow + 1;
    }

    protected void writeSubTitle(Document document, String text)
            throws DocumentException{
        Paragraph titleParagraph = new Paragraph(text, subTitleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);
    }

    protected void writeDocFooter(Document document, PdfWriter pdfWriter){
        PdfContentByte canvas = pdfWriter.getDirectContent();
        Phrase footer = new Phrase("© 2017 by JavaMagicBand - Alex, Nik, Vova", footerFont);
        ColumnText.showTextAligned(
                canvas,
                Element.ALIGN_CENTER,
                footer,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() + 10,
                0
        );
    }

    protected static void renderDocBorder(Document document, PdfWriter writer){
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = new Rectangle(document.getPageSize());
        float top = rect.getTop();
        float bottom = rect.getBottom();
        rect.setTop(top - borderMargin);
        rect.setBottom(bottom + borderMargin);
        rect.setLeft(rect.getLeft() + borderMargin);
        rect.setRight(rect.getRight() - borderMargin);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        canvas.rectangle(rect);
    }

    protected void writeDocBeanTable(Document document, Field[] beanFields, List<? extends Bean> beans) throws Exception{
        //write header table row
        PdfPTable table = new PdfPTable(beanFields.length);
        for(int i = 0; i < beanFields.length; i++){
            table.addCell(new PdfPCell(new Phrase(
                    beanFields[i].getName()
            )));
        }
        //write bean rows
        for(Bean bean : beans){
            for(Field field : beanFields){
                table.addCell(new PdfPCell(new Phrase(
                        field.get(bean).toString()
                )));
            }
        }

        document.add(table);
    }

    protected void writeSheetBeanTable(HSSFWorkbook workbook, HSSFSheet sheet, Field[] beanFields, List<? extends Bean> beans, int headerRowIndex)
        throws Exception{
        //cells styles
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

        HSSFCellStyle bottomCellStyle = workbook.createCellStyle();
        bottomCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bottomCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        bottomCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        //write header table row
        int currRowIndex = headerRowIndex;
        Row headerRow = sheet.createRow(headerRowIndex);
        for(int i = 0; i < beanFields.length; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(beanFields[i].getName());
            cell.setCellStyle(headerCellStyle);
        }
        currRowIndex++;
        //write bean rows
        for(int j = 0; j < beans.size(); j++){
            Row row = sheet.createRow(currRowIndex);
            for(int i = 0; i < beanFields.length; i++){
                Cell cell = row.createCell(i);
                setSheetRowCellValue(cell, beanFields[i].get(beans.get(j)));
                if(j == beans.size() -1){
                    cell.setCellStyle(bottomCellStyle);
                }else{
                    cell.setCellStyle(cellStyle);
                }
            }
            currRowIndex++;
        }

        for(int j = 0; j < beanFields.length; j++){
            sheet.autoSizeColumn(j);
        }
    }

    private void borderRegion(CellRangeAddress region, HSSFWorkbook workbook, HSSFSheet sheet){
        short borderStyle = CellStyle.BORDER_MEDIUM;
        RegionUtil.setBorderBottom(borderStyle, region, sheet, workbook);
        RegionUtil.setBorderTop(borderStyle, region, sheet, workbook);
        RegionUtil.setBorderLeft(borderStyle, region, sheet, workbook);
        RegionUtil.setBorderRight(borderStyle, region, sheet, workbook);
    }

    private void setSheetRowCellValue(Cell cell, Object value){
        if(value instanceof Number){
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(((Number) value).doubleValue());
        }else{
            cell.setCellType(CellType.STRING);
            cell.setCellValue(value.toString());
        }
    }

    protected void writeXmlBeanList(
            org.w3c.dom.Document document,
            org.w3c.dom.Element listElement,
            String beanTagName,
            Field[] beanFields,
            List<? extends Bean> beans
    ) throws Exception{
        for(Bean bean : beans){
            org.w3c.dom.Element beanElement = document.createElement(beanTagName);
            for(Field field : beanFields){
                org.w3c.dom.Element fieldElement = document.createElement(field.getName());
                fieldElement.appendChild(document.createTextNode(
                        field.get(bean).toString()
                ));
                beanElement.appendChild(fieldElement);
            }
            listElement.appendChild(beanElement);
        }
    }

    private Paragraph getDocumentGenerationInfoParagraph() throws Exception{
        DocumentGenerationInfo documentGenerationInfo = getDocumentGenerationInfo();
        Paragraph paragraph = new Paragraph();
        Paragraph genTimeParagraph = new Paragraph("Generation time: " + documentGenerationInfo.generationTime, textFont);
        genTimeParagraph.setAlignment(Element.ALIGN_LEFT);
        Paragraph genByParagraph = new Paragraph("Generated by: " + documentGenerationInfo.generatedBy, textFont);
        genByParagraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(genTimeParagraph);
        paragraph.add(genByParagraph);
        return paragraph;
    }

    protected DocumentGenerationInfo getDocumentGenerationInfo() throws Exception{
        DocumentGenerationInfo documentGenerationInfo = new DocumentGenerationInfo();
        documentGenerationInfo.generatedBy = "Somebody";
//        documentGenerationInfo.generatedBy = session.getUserName();
        documentGenerationInfo.generationTime = new SimpleDate();
        return documentGenerationInfo;
    }

    protected static Font reportTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD | Font.UNDERLINE);
    protected static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    protected static Font subTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    protected static Font textFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    protected static Font footerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);

    protected final IDatabaseMap databaseMap;
    protected final IServiceUnitOfWork serviceUnitOfWork;

    protected static int documentMargin = 50;
    protected static int borderMargin = documentMargin / 2;
    protected static int documentFooterSize = 20;

    private static String excelSheetContentType = "application/vnd.ms-excel";
    private static String pdfDocumentContentType = "application/pdf";
    private static String xmlDocumentContentType = "text/xml;charset=UTF-8";
    private static String xmlDocumentExtension = ".xml";
    private static String pdfDocumentExtension = ".pdf";
    private static String excelSheetExtension = ".xls";

//    private final ISession session;
}
