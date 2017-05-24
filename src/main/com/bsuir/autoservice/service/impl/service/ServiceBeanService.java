package main.com.bsuir.autoservice.service.impl.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public class ServiceBeanService implements IServiceBeanService {

    @Inject
    public ServiceBeanService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;

    @Override
    public List<Service> getAvailableServices() throws ServiceException {
        try {
            return daoUnitOfWork.getServiceDao().readAll();
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
