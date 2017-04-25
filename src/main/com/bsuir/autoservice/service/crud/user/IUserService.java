package main.com.bsuir.autoservice.service.crud.user;

import main.com.bsuir.autoservice.bean.user;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

public interface IUserService extends IServiceCrud<Integer, user> {
    Boolean checkLogin(String login, String password) throws ServiceException;
    Boolean resetLogin(String email) throws ServiceException;
}
