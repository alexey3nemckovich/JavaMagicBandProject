package main.com.bsuir.autoservice.command.document;

import com.google.inject.Inject;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.command.param.DocumentInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

public class GenerateUserListDocumentCommand extends GenerateDocumentCommand{

    @Inject
    public GenerateUserListDocumentCommand(/*ISession session, */IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap) {
        super(/*session, */serviceUnitOfWork, databaseMap);
    }

    protected void fillPdfDoc(Document document, PdfWriter pdfWriter, DocumentInfo documentInfo) throws Exception{
        writeTitle(document, "Service users");
        User user = new User();
        Field[] userFields = user.getRenderFields();
        List<User> users = serviceUnitOfWork.getUserService().getAllUsers();
        writeDocBeanTable(document, userFields, users);
        addDocEmptyLine(document, 2);
        writeReportSummary(document, pdfWriter, users.size());
    }

    private void writeReportSummary(Document document, PdfWriter pdfWriter, int countUsers)
        throws Exception{
        StringBuilder summary = new StringBuilder();
        if(countUsers < 10){
            summary.append("According to report data, we can make conclusion, that service shop popularity is low." +
                    "We should make more to decise this problem.");
        }else{
            summary.append("According to report data, we can make conclusion, that service shop popularity is normal." +
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
        User user = new User();
        Field[] userFields = user.getRenderFields();
        List<User> users = serviceUnitOfWork.getUserService().getAllUsers();
        writeSheetBeanTable(workbook, sheet, userFields, users, startRowIndex);
    }

    @Override
    protected void fillXmlDoc(
            org.w3c.dom.Document document,
            org.w3c.dom.Element reportContentElement,
            DocumentInfo documentInfo
    ) throws Exception{
        User user = new User();
        Field[] userFields = user.getRenderFields();
        List<User> users = serviceUnitOfWork.getUserService().getAllUsers();
        writeXmlBeanList(document, reportContentElement, "user", userFields, users);
    }

    protected String getDocumentTitle(){
        return "Service users report";
    }

    protected String getDocumentDescription(){
        return "This report gives information about " +
                "all service users.";
    }

    protected String getDocumentSaveFileName(){
        return "UserList";
    }
}
