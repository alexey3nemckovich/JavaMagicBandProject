package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountUpdateGeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountUpdateGeneralInformationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountUpdateGeneralInformationCommand
        implements ICommand<PersonalAccountUpdateGeneralInformationInfo, PersonalAccountUpdateGeneralInformationRet>{
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IUserSession session;

    @Inject
    public PersonalAccountUpdateGeneralInformationCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountUpdateGeneralInformationRet execute(PersonalAccountUpdateGeneralInformationInfo param)
            throws CommandException {
        try{
            return new PersonalAccountUpdateGeneralInformationRet(serviceUnitOfWork.getUserService().
                    updateUserInformation(session.getUserId(), param.getNewUser()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
