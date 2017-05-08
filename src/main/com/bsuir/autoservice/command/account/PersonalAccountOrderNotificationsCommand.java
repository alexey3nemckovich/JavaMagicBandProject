package main.com.bsuir.autoservice.command.account;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountOrderNotificationsInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderNotificationsRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountOrderNotificationsCommand
        implements ICommand<PersonalAccountOrderNotificationsInfo, PersonalAccountOrderNotificationsRet> {
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IUserSession session;

    public PersonalAccountOrderNotificationsCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountOrderNotificationsRet execute(PersonalAccountOrderNotificationsInfo param) throws CommandException {
        try{
            return new PersonalAccountOrderNotificationsRet(
                    serviceUnitOfWork.getNotificationService().getOrderNotifications(session.getUserId(),
                            param.getOrderId(), param.getPageNumber(), param.getNumberPageElements()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
