package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountOrderNotificationsInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderNotificationsRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountOrderNotificationsCommand
        extends AbstractSessionCommand<PersonalAccountOrderNotificationsInfo, PersonalAccountOrderNotificationsRet> {

    @Inject
    public PersonalAccountOrderNotificationsCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountOrderNotificationsRet executeImpl(PersonalAccountOrderNotificationsInfo param)
            throws Exception {
        return new PersonalAccountOrderNotificationsRet(
                serviceUnitOfWork.getNotificationService().getOrderNotifications(session.getUserId(),
                        param.getOrderId(), param.getPageNumber(), param.getNumberPageElements()));
    }
}
