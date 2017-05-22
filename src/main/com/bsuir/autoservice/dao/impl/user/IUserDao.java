package main.com.bsuir.autoservice.dao.impl.user;

import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

public interface IUserDao extends ICrudDao<Integer, User> {
    Integer checkLogin(String login, String password) throws DaoException;
    String getUserName(int idLogin) throws DaoException;
}
