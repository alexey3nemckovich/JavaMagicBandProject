package main.com.bsuir.autoservice.command.bean.page.view;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.Map;

public class GetBeanViewPageCommand implements ICommand<BeanViewPageInfo> {

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
            beanViewPageInfo.totalPagesCount = getTotalPagesCount(beanViewPageInfo.countRecords, serviceCrud);
            setDependencies(beanViewPageInfo, serviceCrud);
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private void setDependencies(BeanViewPageInfo beanViewPageInfo, IServiceCrud serviceCrud) throws ServiceException{
        beanViewPageInfo.dependencyTableNames = serviceCrud.getDependencyTablesNames();
        for(Bean bean: beanViewPageInfo.beans){
            Map<String, Dependency> beanDependencies = serviceCrud.readDependencies(bean);
            beanViewPageInfo.dependencyMap.put(bean, beanDependencies);
        }
    }

    private int getTotalPagesCount(int countRecords, IServiceCrud serviceCrud) throws ServiceException{
        int totalBeanCount = serviceCrud.readTotalCount();
        int totalPagesCount = totalBeanCount/ countRecords;
        if(0 != totalBeanCount% countRecords){
            totalPagesCount++;
        }
        return totalPagesCount;
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
            pageInfo.tableName = "user";
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
