package main.com.bsuir.autoservice.service.impl.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.dto.UserStaffDTO;
import main.com.bsuir.autoservice.service.exception.ServiceException;

public class UserService implements IUserService {

    @Inject
    public UserService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public Integer checkLogin(String login, String password) throws ServiceException {
        try {
            return daoUnitOfWork.getUserDao().checkLogin(login, password);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean resetLogin(String email) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getGeneralInformation(int userId) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateUserInformation(int userId, User newUser) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserStaffDTO getUserStaffInformation(int idLogin) throws ServiceException {
        try {
            return new UserStaffDTO(idLogin, daoUnitOfWork.getUserDao().getUserName(idLogin),
                    daoUnitOfWork.getStaffDao().getSpecialization(idLogin));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
