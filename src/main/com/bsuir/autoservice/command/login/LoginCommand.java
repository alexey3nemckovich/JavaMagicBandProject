package main.com.bsuir.autoservice.command.login;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.LoginInfo;
import main.com.bsuir.autoservice.command.ret.LoginRet;
import main.com.bsuir.autoservice.dto.UserStaffDTO;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.infrastructure.session.exception.SessionException;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class LoginCommand extends AbstractSessionCommand<LoginInfo,LoginRet> {

    @Inject
    public LoginCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected LoginRet executeImpl(LoginInfo param) throws Exception {
        Integer idLogin = serviceUnitOfWork.getUserService().checkLogin(param.getLogin(), param.getPassword());
        boolean isLogin = idLogin != null;

        if (isLogin){
            updateUserSessionInformation(idLogin);
        }

        return new LoginRet(isLogin);
    }

    private void updateUserSessionInformation(int idLogin) throws ServiceException, SessionException {
        UserStaffDTO userStaffDTO = serviceUnitOfWork.getUserService().getUserStaffInformation(idLogin);
        session.update(userStaffDTO.getUserId(), userStaffDTO.getUserName(), userStaffDTO.getStaffLevel());
    }
}
