package main.com.bsuir.autoservice.command.account;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountInformationRet;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountInformationCommand implements ICommand<PersonalAccountInformationInfo,
        PersonalAccountInformationRet>{
    private final IServiceUnitOfWork serviceUnitOfWork;

    public PersonalAccountInformationCommand(IServiceUnitOfWork serviceUnitOfWork) {
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public PersonalAccountInformationRet execute(PersonalAccountInformationInfo param) throws CommandException {
        try {
            PersonalAccountInformationRet.Builder builder =
                    new PersonalAccountInformationRet.Builder().setNestedIsContinueWork(param.isAuthorized());
            return (param.isAuthorized()
                    ? builder
                    .setNestedGeneralUserInformation(
                            serviceUnitOfWork.getUserService().getGeneralInformation(param.getUserId()))
                    .setNestedHaveNewNotification(
                            serviceUnitOfWork.getNotificationService().haveNewNotification())
                    : builder
            ).build();
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
