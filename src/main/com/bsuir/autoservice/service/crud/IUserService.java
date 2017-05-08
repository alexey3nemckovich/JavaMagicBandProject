package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

public interface IUserService extends IServiceCrud<Integer, User> {
    boolean checkLogin(String login, String password) throws ServiceException;
    boolean resetLogin(String email) throws ServiceException;
    User getGeneralInformation(int userId) throws ServiceException;
}
