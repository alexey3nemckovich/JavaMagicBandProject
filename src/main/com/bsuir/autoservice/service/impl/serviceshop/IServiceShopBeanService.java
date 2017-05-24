package main.com.bsuir.autoservice.service.impl.serviceshop;

import main.com.bsuir.autoservice.bean.impl.ServiceShop;
import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IServiceShopBeanService extends IService {
    List<ServiceShop> getFullWorkingShop() throws ServiceException;
    List<Staff> getStaffList(int serviceShopId) throws ServiceException;
}
