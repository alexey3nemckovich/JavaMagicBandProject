package main.com.bsuir.autoservice.service.crud.impl.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.service;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceBeanService extends AbstractServiceCrud<Integer, service> implements IServiceBeanService{

    @Inject
    public ServiceBeanService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getServiceDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(service bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            dependencies.addAll(
                    Arrays.asList(
                            getDependencyForTable(daoUnitOfWork.getDiscountDao(), "service_id", bean.getId()),
                            getDependencyForTable(daoUnitOfWork.getOrderedServiceDao(), "service_id", bean.getId())
                    )
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
