package main.com.bsuir.autoservice.command.bean.page.view;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanViewPageCommand implements ICommand<BeanViewPageInfo, BeanViewPageInfo> {

    @Inject
    public GetBeanViewPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo)
            throws CommandException {
        try {
            CheckPageInfo(beanViewPageInfo);
            int index = 0;
            if(1 != beanViewPageInfo.page){
                index = (beanViewPageInfo.page - 1) * beanViewPageInfo.countRecords;
            }
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanViewPageInfo.tableName);
            beanViewPageInfo.beans = serviceCrud.read(index, beanViewPageInfo.countRecords);
            int totalBeanCount = serviceCrud.readTotalCount();
            beanViewPageInfo.totalPagesCount = totalBeanCount/ beanViewPageInfo.countRecords;
            if(0 != totalBeanCount% beanViewPageInfo.countRecords){
                beanViewPageInfo.totalPagesCount++;
            }
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private void CheckPageInfo(BeanViewPageInfo pageInfo)
            throws ServiceException{
        if(0 == pageInfo.page) {
            pageInfo.page = 1;
        }
        if(0 == pageInfo.countRecords){
            pageInfo.countRecords = 3;
        }
        if(pageInfo.tableName.isEmpty()){
            pageInfo.tableName = "User";
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
