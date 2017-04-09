package main.com.bsuir.autoservice.command.bean.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class DeleteBeanCommand implements ICommand<BeanViewPageInfo> {

    @Inject
    public DeleteBeanCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo) throws CommandException {
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanViewPageInfo.tableName);
            Bean bean = Bean.getBeanObject(beanViewPageInfo.tableName, beanViewPageInfo.fields);
            serviceCrud.delete(bean);
            beanViewPageInfo.result = "Operation success";
            beanViewPageInfo.beans.remove(bean);
            //change page content
            int totalBeanCount = serviceCrud.readTotalCount();
            beanViewPageInfo.totalPagesCount = totalBeanCount / beanViewPageInfo.countRecords;
            if(0 != totalBeanCount% beanViewPageInfo.countRecords){
                beanViewPageInfo.totalPagesCount++;
            }
            if(beanViewPageInfo.totalPagesCount < beanViewPageInfo.page){
                beanViewPageInfo.page--;
            }
            int index = 0;
            if(1 != beanViewPageInfo.page){
                index = (beanViewPageInfo.page - 1) * beanViewPageInfo.countRecords;
            }
            beanViewPageInfo.beans = serviceCrud.read(index, beanViewPageInfo.countRecords);
            return beanViewPageInfo;
        }catch (Exception e){
            beanViewPageInfo.result = "Failed to delete record.";
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
