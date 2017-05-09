package main.com.bsuir.autoservice.command.bean.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.BeanException;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.exception.ExceptionUnwrapper;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class DeleteBeanCommand implements ICommand<BeanViewPageInfo, BeanViewPageInfo> {

    private final IDatabaseMap databaseMap;

    @Inject
    public DeleteBeanCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.databaseMap = databaseMap;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo) throws CommandException {
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanViewPageInfo.tableName);
            Bean bean = databaseMap.getBeanInstance(beanViewPageInfo.tableName, beanViewPageInfo.fields);
            serviceCrud.delete(bean);
            beanViewPageInfo.result = "Operation success";
            //change page content
            int totalBeanCount = serviceCrud.readTotalCount();
            beanViewPageInfo.totalPagesCount = totalBeanCount / beanViewPageInfo.countRecords;
            if (0 != totalBeanCount % beanViewPageInfo.countRecords) {
                beanViewPageInfo.totalPagesCount++;
            }
            if (beanViewPageInfo.totalPagesCount < beanViewPageInfo.page) {
                beanViewPageInfo.page--;
            }
            int index = 0;
            if (1 != beanViewPageInfo.page) {
                index = (beanViewPageInfo.page - 1) * beanViewPageInfo.countRecords;
            }
            beanViewPageInfo.beans = serviceCrud.read(index, beanViewPageInfo.countRecords);
            return beanViewPageInfo;
        }catch (ServiceException | BeanException e){
            //log
            beanViewPageInfo.result = String.format(
                    "Failed to delete record: %s",
                    ExceptionUnwrapper.getRootException(e).getMessage()
            );
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
