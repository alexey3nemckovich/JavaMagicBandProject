package main.com.bsuir.autoservice.service.impl;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;

public interface IUserService extends IServiceCrud<Integer, User> {
    boolean checkLogin(String login, String password) throws ServiceException;
    boolean resetLogin(String email) throws ServiceException;
    User getGeneralInformation(int userId) throws ServiceException;
    boolean updateUserInformation(int userId, User newUser) throws ServiceException;
}
