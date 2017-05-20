package main.com.bsuir.autoservice.service.impl.serviceshop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;

public class ServiceShopBeanService implements IServiceShopBeanService{

    @Inject
    public ServiceShopBeanService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
