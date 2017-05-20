package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountAvailableOrderServicesInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountAvailableOrderServicesRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountAvailableOrderServicesCommand extends AbstractSessionCommand<
        PersonalAccountAvailableOrderServicesInfo, PersonalAccountAvailableOrderServicesRet> {

    @Inject
    public PersonalAccountAvailableOrderServicesCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountAvailableOrderServicesRet executeImpl(PersonalAccountAvailableOrderServicesInfo param)
            throws Exception {
        return new PersonalAccountAvailableOrderServicesRet(
                serviceUnitOfWork.getServiceBeanService().getAvailableServices(),
                serviceUnitOfWork.getShareService().getActiveAccountShares(session.getUserId()));
    }
}
