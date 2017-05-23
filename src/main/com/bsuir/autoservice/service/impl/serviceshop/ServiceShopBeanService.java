package main.com.bsuir.autoservice.service.impl.serviceshop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.ServiceShop;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public class ServiceShopBeanService implements IServiceShopBeanService{

    @Inject
    public ServiceShopBeanService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;

    @Override
    public List<ServiceShop> getFullWorkingShop() throws ServiceException {
        try {
            return daoUnitOfWork.getServiceShopDao().getFullWorking();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
