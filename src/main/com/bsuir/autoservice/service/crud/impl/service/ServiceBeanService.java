package main.com.bsuir.autoservice.service.crud.impl.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.service;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

public class ServiceBeanService extends AbstractServiceCrud<Integer, service> implements IServiceBeanService{

    @Inject
    public ServiceBeanService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getServiceDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<String> getDependencyTablesNames(){
        List<String> dependencyTableNames = new ArrayList<>();
        dependencyTableNames.add(daoUnitOfWork.getDiscountDao().getTableName());
        dependencyTableNames.add(daoUnitOfWork.getOrderedServiceDao().getTableName());
        return dependencyTableNames;
    }

    @Override
    public Map<String, Dependency> readDependencies(service bean) throws ServiceException {
        try {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();
            dependencies.put(
                    daoUnitOfWork.getDiscountDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getDiscountDao(), "service_id", bean.getId())
            );
            dependencies.put(
                    daoUnitOfWork.getOrderedServiceDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getOrderedServiceDao(), "service_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
