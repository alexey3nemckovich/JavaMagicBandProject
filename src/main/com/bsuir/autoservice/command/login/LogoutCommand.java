package main.com.bsuir.autoservice.command.login;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.LogoutInfo;
import main.com.bsuir.autoservice.command.ret.LogoutRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class LogoutCommand extends AbstractSessionCommand<LogoutInfo, LogoutRet>{

    @Inject
    public LogoutCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected LogoutRet executeImpl(LogoutInfo param) throws Exception {
        return new LogoutRet(session.clear());
    }
}
