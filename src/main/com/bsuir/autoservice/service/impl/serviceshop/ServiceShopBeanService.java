package main.com.bsuir.autoservice.service.impl.serviceshop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceShopBeanService implements IServiceShopBeanService{

    @Inject
    public ServiceShopBeanService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Staff> getStaffList(int shopId) throws ServiceException{
        try {
            Map<String, String> condition = new HashMap<>();
            condition.put("service_shop_id", String.valueOf(shopId));
            return daoUnitOfWork.getStaffDao().read(condition);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
