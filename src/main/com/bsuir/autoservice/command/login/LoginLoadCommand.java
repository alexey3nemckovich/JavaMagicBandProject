package main.com.bsuir.autoservice.command.login;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.LoginLoadInfo;
import main.com.bsuir.autoservice.command.ret.LoginLoadRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class LoginLoadCommand extends AbstractSessionCommand<LoginLoadInfo, LoginLoadRet>{

    @Inject
    public LoginLoadCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected LoginLoadRet executeImpl(LoginLoadInfo param) throws Exception {
        boolean isAuthorized = session.isAuthorized();
        LoginLoadRet.Builder loginLoadRetBuilder = new LoginLoadRet.Builder().setAuthorized(isAuthorized);
        if (isAuthorized){
            loginLoadRetBuilder.setUserName(session.getUserName());
        }
        return loginLoadRetBuilder.build();
    }
}
