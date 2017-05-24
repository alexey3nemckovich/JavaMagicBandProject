package main.com.bsuir.autoservice.command.document;

import com.google.inject.Inject;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import main.com.bsuir.autoservice.bean.impl.SparePart;
import main.com.bsuir.autoservice.command.param.DocumentInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

public class GenerateSparePartListDocumentCommand extends GenerateDocumentCommand{

    @Inject
    public GenerateSparePartListDocumentCommand(/*ISession session, */IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap) {
        super(/*session, */serviceUnitOfWork, databaseMap);
    }

    protected void fillPdfDoc(Document document, PdfWriter pdfWriter, DocumentInfo documentInfo) throws Exception{
        writeTitle(document, "Available spare parts");
        SparePart sparePart = new SparePart();
        Field[] sparePartFields = sparePart.getFieldsOrdered();
        List<SparePart> sparePartList = serviceUnitOfWork.getSparePartService().getAvailableSparePartList();
        writeDocBeanTable(document, sparePartFields, sparePartList);
    }

    protected void fillSheet(HSSFWorkbook workbook, HSSFSheet sheet, DocumentInfo documentInfo, int startRowIndex) throws Exception{
        SparePart sparePart = new SparePart();
        Field[] sparePartFields = sparePart.getFieldsOrdered();
        List<SparePart> sparePartList = serviceUnitOfWork.getSparePartService().getAvailableSparePartList();
        writeSheetBeanTable(workbook, sheet, sparePartFields, sparePartList, startRowIndex);
    }

    @Override
    protected void fillXmlDoc(
            org.w3c.dom.Document document,
            org.w3c.dom.Element reportContentElement,
            DocumentInfo documentInfo
    ) throws Exception{
        SparePart sparePart = new SparePart();
        Field[] sparePartFieldsOrdered = sparePart.getFieldsOrdered();
        List<SparePart> sparePartList = serviceUnitOfWork.getSparePartService().getAvailableSparePartList();
        writeXmlBeanList(document, reportContentElement, "spare_part", sparePartFieldsOrdered, sparePartList);
    }

    protected String getDocumentTitle(){
        return "Spare part report";
    }

    protected String getDocumentDescription(){
        return "This report gives information about " +
                "all available spare parts.";
    }

    protected String getDocumentSaveFileName(){
        return "AvailableSparePartList";
    }
}
