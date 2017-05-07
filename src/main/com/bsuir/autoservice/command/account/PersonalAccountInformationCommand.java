package main.com.bsuir.autoservice.command.account;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountInformationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountInformationCommand implements ICommand<PersonalAccountInformationInfo,
        PersonalAccountInformationRet>{
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IUserSession session;

    public PersonalAccountInformationCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountInformationRet execute(PersonalAccountInformationInfo param) throws CommandException {
        try {
            return new PersonalAccountInformationRet(
                    serviceUnitOfWork.getUserService().getGeneralInformation(session.getUserId()),
                    serviceUnitOfWork.getNotificationService().haveNewNotification());
        } catch (Exception e) {
            throw new CommandException(e);
        }
    }
}
