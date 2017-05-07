package main.com.bsuir.autoservice.command.bean.page.view;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanViewPageCommand implements ICommand<BeanViewPageInfo> {

    @Inject
    public GetBeanViewPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo)
            throws CommandException {
        try {
            int index = 0;
            if(1 != beanViewPageInfo.page){
                index = (beanViewPageInfo.page - 1) * beanViewPageInfo.countRecords;
            }
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanViewPageInfo.tableName);
            beanViewPageInfo.beans = serviceCrud.read(index, beanViewPageInfo.countRecords);
            beanViewPageInfo.totalPagesCount = getTotalPagesCount(beanViewPageInfo.countRecords, serviceCrud);
            beanViewPageInfo.dependencyTableNames = serviceCrud.getDependencyTablesNames();
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private int getTotalPagesCount(int countRecords, IServiceCrud serviceCrud) throws ServiceException{
        int totalBeanCount = serviceCrud.readTotalCount();
        int totalPagesCount = totalBeanCount/ countRecords;
        if(0 != totalBeanCount % countRecords){
            totalPagesCount++;
        }
        return totalPagesCount;
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
