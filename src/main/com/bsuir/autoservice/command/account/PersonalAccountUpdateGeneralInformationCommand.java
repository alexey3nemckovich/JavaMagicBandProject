package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountUpdateGeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountUpdateGeneralInformationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;


public class PersonalAccountUpdateGeneralInformationCommand
        extends AbstractSessionCommand<PersonalAccountUpdateGeneralInformationInfo, PersonalAccountUpdateGeneralInformationRet>{

    @Inject
    public PersonalAccountUpdateGeneralInformationCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountUpdateGeneralInformationRet executeImpl(PersonalAccountUpdateGeneralInformationInfo param) throws Exception {
        return new PersonalAccountUpdateGeneralInformationRet(serviceUnitOfWork.getUserService().
                updateUserInformation(session.getUserId(), param.getNewUser()));
    }
}
