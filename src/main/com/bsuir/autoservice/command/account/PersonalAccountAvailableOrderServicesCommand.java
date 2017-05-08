package main.com.bsuir.autoservice.command.account;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountAvailableOrderServicesInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountAvailableOrderServicesRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountAvailableOrderServicesCommand implements
        ICommand<PersonalAccountAvailableOrderServicesInfo, PersonalAccountAvailableOrderServicesRet> {
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IUserSession session;

    public PersonalAccountAvailableOrderServicesCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountAvailableOrderServicesRet execute(PersonalAccountAvailableOrderServicesInfo param)
            throws CommandException {
        try{
            return new PersonalAccountAvailableOrderServicesRet(
                    serviceUnitOfWork.getServiceService().getAvailableServices(),
                    serviceUnitOfWork.getShareService().getActiveAccountShares(session.getUserId()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
