package main.com.bsuir.autoservice.command.document;

import com.google.inject.Inject;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.command.param.ShopStaffListDocumentInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

public class GenerateServiceShopStaffListDocumentCommand extends GenerateDocumentCommand<ShopStaffListDocumentInfo>{

    @Inject
    public GenerateServiceShopStaffListDocumentCommand(/*ISession session, */IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap) {
        super(/*session, */serviceUnitOfWork, databaseMap);
    }

    @Override
    protected void fillPdfDoc(Document document, PdfWriter writer, ShopStaffListDocumentInfo documentInfo) throws Exception{
        writeTitle(document, "Shop staff list");
        Staff staff = new Staff();
        Field[] staffFieldsOrdered = staff.getFieldsOrdered();
        List<Staff> staffList = serviceUnitOfWork.getServiceShopBeanService().getStaffList(documentInfo.shopId);
        writeDocBeanTable(document, staffFieldsOrdered, staffList);
    }

    protected void fillSheet(HSSFWorkbook workbook, HSSFSheet sheet, ShopStaffListDocumentInfo documentInfo, int startRowIndex) throws Exception{
        Staff staff = new Staff();
        Field[] staffFieldsOrdered = staff.getFieldsOrdered();
        List<Staff> staffList = serviceUnitOfWork.getServiceShopBeanService().getStaffList(documentInfo.shopId);
        writeSheetBeanTable(workbook, sheet, staffFieldsOrdered, staffList, startRowIndex);
    }

    @Override
    protected void fillXmlDoc(
            org.w3c.dom.Document document,
            org.w3c.dom.Element reportContentElement,
            ShopStaffListDocumentInfo documentInfo
    ) throws Exception{
        Staff staff = new Staff();
        Field[] staffFields = staff.getFieldsOrdered();
        List<Staff> staffList = serviceUnitOfWork.getServiceShopBeanService().getStaffList(documentInfo.shopId);
        writeXmlBeanList(document, reportContentElement, "staff", staffFields, staffList);
    }

    protected String getDocumentTitle(){
        return "Service shop staff list report";
    }

    protected String getDocumentDescription(){
        return "This report gives information about " +
                "all shop workers.";
    }

    protected String getDocumentSaveFileName(){
        return "ServiceShopStaffList";
    }
}
