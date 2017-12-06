package main.com.bsuir.autoservice.service.crud.impl.backup.ordered_service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.ordered_service;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

public class OrderedServiceBeanService extends AbstractServiceCrud<NullType, ordered_service> implements IOrderedServiceBeanService{

    @Inject
    public OrderedServiceBeanService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderedServiceDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(ordered_service bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
