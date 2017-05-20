package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountInformationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountInformationCommand extends AbstractSessionCommand<PersonalAccountInformationInfo,
        PersonalAccountInformationRet>{

    @Inject
    public PersonalAccountInformationCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountInformationRet executeImpl(PersonalAccountInformationInfo param) throws Exception {
        return new PersonalAccountInformationRet(
                serviceUnitOfWork.getUserService().getGeneralInformation(session.getUserId()),
                serviceUnitOfWork.getNotificationService().haveNewNotification());
    }
}
