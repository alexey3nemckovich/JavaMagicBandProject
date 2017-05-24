package main.com.bsuir.autoservice.command.document;

import com.google.inject.Inject;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.command.param.DocumentInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

public class GenerateShareListDocumentCommand extends GenerateDocumentCommand{

    @Inject
    public GenerateShareListDocumentCommand(/*ISession session, */IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap) {
        super(/*session, */serviceUnitOfWork, databaseMap);
    }

    protected void fillPdfDoc(Document document, PdfWriter pdfWriter, DocumentInfo documentInfo) throws Exception{
        writeTitle(document, "Active shares");
        Share share = new Share();
        Field[] shareFields = share.getFieldsOrdered();
        List<Share> shareList = serviceUnitOfWork.getShareService().getActiveShares();
        writeDocBeanTable(document, shareFields, shareList);
    }

    protected void fillSheet(HSSFWorkbook workbook, HSSFSheet sheet, DocumentInfo documentInfo, int startRowIndex) throws Exception{
        Share share = new Share();
        Field[] shareFields = share.getFieldsOrdered();
        List<Share> shareList = serviceUnitOfWork.getShareService().getActiveShares();
        writeSheetBeanTable(workbook, sheet, shareFields, shareList, startRowIndex);
    }

    @Override
    protected void fillXmlDoc(
            org.w3c.dom.Document document,
            org.w3c.dom.Element reportContentElement,
            DocumentInfo documentInfo
    ) throws Exception{
        Share share = new Share();
        Field[] shareFields = share.getFieldsOrdered();
        List<Share> shareList = serviceUnitOfWork.getShareService().getActiveShares();
        writeXmlBeanList(document, reportContentElement, "share", shareFields, shareList);
    }

    protected String getDocumentTitle(){
        return "Active shares list report";
    }

    protected String getDocumentDescription(){
        return "This report gives information about " +
                "all active shares.";
    }

    protected String getDocumentSaveFileName(){
        return "ShareList";
    }
}
