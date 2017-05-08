package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountShareInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountShareRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountShareCommand implements ICommand<PersonalAccountShareInfo, PersonalAccountShareRet> {
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IUserSession session;

    @Inject
    public PersonalAccountShareCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountShareRet execute(PersonalAccountShareInfo param) throws CommandException {
        try {
            return new PersonalAccountShareRet(serviceUnitOfWork.getShareService().getActiveAccountShares(
                    session.getUserId()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
