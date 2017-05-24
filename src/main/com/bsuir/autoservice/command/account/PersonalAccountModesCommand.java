package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.NoInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountModesRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;


public class PersonalAccountModesCommand extends AbstractSessionCommand<NoInfo, PersonalAccountModesRet>{

    @Inject
    public PersonalAccountModesCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountModesRet executeImpl(NoInfo param) throws Exception {
        return new PersonalAccountModesRet(session.getUserLevel());
    }
}
