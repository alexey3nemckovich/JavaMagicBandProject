package main.com.bsuir.autoservice.service.impl.user;

import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

public interface IUserService extends IService {
    boolean checkLogin(String login, String password) throws ServiceException;
    boolean resetLogin(String email) throws ServiceException;
    User getGeneralInformation(int userId) throws ServiceException;
    boolean updateUserInformation(int userId, User newUser) throws ServiceException;
}
