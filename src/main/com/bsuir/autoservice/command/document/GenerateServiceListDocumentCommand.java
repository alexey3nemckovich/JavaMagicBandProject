package main.com.bsuir.autoservice.command.document;

import com.google.inject.Inject;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.command.param.DocumentInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GenerateServiceListDocumentCommand extends GenerateDocumentCommand {

    @Inject
    public GenerateServiceListDocumentCommand(/*ISession session, */IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap) {
        super(/*session, */serviceUnitOfWork, databaseMap);
    }

    protected void fillPdfDoc(Document document, PdfWriter writer, DocumentInfo documentInfo) throws Exception{
        writeTitle(document, "Available services");
        Service service = new Service();
        Field[] serviceFields = service.getFieldsOrdered();
        List<Service> serviceList = serviceUnitOfWork.getServiceBeanService().getAvailableServices();
        writeDocBeanTable(document, serviceFields, serviceList);addDocEmptyLine(document, 2);
        writeReportSummary(document, writer, serviceList.size());
    }

    private void writeReportSummary(Document document, PdfWriter pdfWriter, int countServices)
            throws Exception{
        StringBuilder summary = new StringBuilder();
        if(countServices < 10){
            summary.append("We should find reasons for such low count of services." +
                    "This problem can make clients searching for places with more services enabled");
        }else{
            summary.append("According to report data, we can make conclusion, that we have enough count of services." +
                    "But we should work harder to do our bests.");
        }

        Paragraph summaryHeader = new Paragraph("Report summary:", textFont);
        summaryHeader.setAlignment(Element.ALIGN_LEFT);
        Paragraph summaryContent = new Paragraph();
        summaryContent.setFont(textFont);
        summaryContent.add(new Phrase(summary.toString()));
        document.add(summaryHeader);
        document.add(summaryContent);
    }

    protected void fillSheet(HSSFWorkbook workbook, HSSFSheet sheet, DocumentInfo documentInfo, int startRowIndex) throws Exception{
        Service service = new Service();
        Field[] serviceFields = service.getFieldsOrdered();
        List<Service> services = new ArrayList<>();
        writeSheetBeanTable(workbook, sheet, serviceFields, services, startRowIndex);
    }

    @Override
    protected void fillXmlDoc(
            org.w3c.dom.Document document,
            org.w3c.dom.Element reportContentElement,
            DocumentInfo documentInfo
    ) throws Exception{
        Service service = new Service();
        Field[] serviceFields = service.getFieldsOrdered();
        List<Service> services = serviceUnitOfWork.getServiceBeanService().getAvailableServices();
        writeXmlBeanList(document, reportContentElement, "service", serviceFields, services);
    }

    protected String getDocumentTitle(){
        return "Available services report";
    }

    protected String getDocumentDescription(){
        return "This report gives information about " +
                "all available services at the moment.";
    }

    protected String getDocumentSaveFileName(){
        return "AvailableServicesList";
    }
}
