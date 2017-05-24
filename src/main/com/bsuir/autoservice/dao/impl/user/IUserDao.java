package main.com.bsuir.autoservice.dao.impl.user;

import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dto.UserGeneralInformationDTO;
import main.com.bsuir.autoservice.dto.UserUpdateInformationDTO;

public interface IUserDao extends ICrudDao<Integer, User> {
    Integer checkLogin(String login, String password) throws DaoException;
    String getUserName(int idLogin) throws DaoException;
    UserGeneralInformationDTO getUserGeneralInformation(int userId) throws DaoException;
    boolean updateGeneralInformation(int userId, UserUpdateInformationDTO newUser) throws DaoException;
    boolean updatePassword(int userId, String newPassword) throws DaoException;
}
