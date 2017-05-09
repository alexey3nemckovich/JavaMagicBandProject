package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountShareInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountShareRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountShareCommand extends
        AbstractSessionCommand<PersonalAccountShareInfo, PersonalAccountShareRet> {

    @Inject
    public PersonalAccountShareCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountShareRet executeImpl(PersonalAccountShareInfo param) throws Exception {
        return new PersonalAccountShareRet(serviceUnitOfWork.getShareService().getActiveAccountShares(
                session.getUserId()));
    }
}
