package main.com.bsuir.autoservice.command.bean.page;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanPageCommand implements ICommand<BeanPageInfo> {
    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public GetBeanPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanPageInfo execute(BeanPageInfo pageInfo)
            throws CommandException {
        try {
            CheckPageInfo(pageInfo);
            int index = 0;
            if(1 != pageInfo.page){
                index = (pageInfo.page - 1) * pageInfo.countRecords;
            }
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(pageInfo.table);
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

    private void CheckPageInfo(BeanPageInfo pageInfo)
            throws ServiceException{
        if(0 == pageInfo.page) {
            pageInfo.page = 1;
        }
        if(0 == pageInfo.countRecords){
            pageInfo.countRecords = 3;
        }
        if(pageInfo.table.isEmpty()){
            pageInfo.table = "user";
        }
    }
}
