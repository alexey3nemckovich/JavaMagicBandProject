package main.com.bsuir.autoservice.service.impl.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public class ServiceBeanService implements IServiceBeanService {

    @Inject
    public ServiceBeanService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;

    @Override
    public List<ServiceAvailableDTO> getAvailableServices() throws ServiceException {
        try {
            return daoUnitOfWork.getServiceDao().getAvailable();
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Service> getFullAvailableServices() throws ServiceException {
        try{
            return daoUnitOfWork.getServiceDao().getFullAvailable();
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
