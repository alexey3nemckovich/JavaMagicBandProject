package main.com.bsuir.autoservice.command.bean.page.view;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanViewPageCommand implements ICommand<BeanViewPageInfo> {

    @Inject
    public GetBeanViewPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo pageInfo)
            throws CommandException {
        try {
            CheckPageInfo(pageInfo);
            int index = 0;
            if(1 != pageInfo.page){
                index = (pageInfo.page - 1) * pageInfo.countRecords;
            }
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(pageInfo.name);
            pageInfo.beans = serviceCrud.read(index, pageInfo.countRecords);
            int totalBeanCount = serviceCrud.readTotalCount();
            pageInfo.totalPagesCount = totalBeanCount/ pageInfo.countRecords;
            if(0 != totalBeanCount% pageInfo.countRecords){
                pageInfo.totalPagesCount++;
            }
            return pageInfo;
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
        if(pageInfo.name.isEmpty()){
            pageInfo.name = "user";
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
