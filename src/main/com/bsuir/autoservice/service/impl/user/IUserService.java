package main.com.bsuir.autoservice.service.impl.user;

import main.com.bsuir.autoservice.dto.UserGeneralInformationDTO;
import main.com.bsuir.autoservice.dto.UserStaffDTO;
import main.com.bsuir.autoservice.dto.UserUpdateInformationDTO;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

public interface IUserService extends IService {
    Integer checkLogin(String login, String password) throws ServiceException;
    boolean resetLogin(String email) throws ServiceException;
    UserGeneralInformationDTO getGeneralInformation(int userId) throws ServiceException;
    boolean updateUserInformation(int userId, UserUpdateInformationDTO newUser) throws ServiceException;
    UserStaffDTO getUserStaffInformation(int idLogin) throws ServiceException;
    boolean updateUserPassword(int userId, String newPassword) throws ServiceException;
}
