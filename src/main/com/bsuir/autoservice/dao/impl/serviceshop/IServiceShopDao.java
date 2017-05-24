package main.com.bsuir.autoservice.dao.impl.serviceshop;

import main.com.bsuir.autoservice.bean.impl.ServiceShop;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

import java.util.List;

public interface IServiceShopDao extends ICrudDao<Integer, ServiceShop> {
    List<ServiceShop> getFullWorking() throws DaoException;
}
