package main.com.bsuir.autoservice.dao.impl.service;

import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;

import java.util.List;

public interface IServiceDao extends ICrudDao<Integer, Service> {
    List<ServiceAvailableDTO> getAvailable() throws DaoException;
    List<Service> getFullAvailable() throws DaoException;
    List<Service> getConcreteServices(List<Integer> allUsers) throws DaoException;
}
