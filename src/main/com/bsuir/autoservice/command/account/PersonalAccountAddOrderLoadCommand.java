package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountAddOrderLoadInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountAddOrderLoadRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountAddOrderLoadCommand
        extends AbstractSessionCommand<PersonalAccountAddOrderLoadInfo, PersonalAccountAddOrderLoadRet>{

    @Inject
    public PersonalAccountAddOrderLoadCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountAddOrderLoadRet executeImpl(PersonalAccountAddOrderLoadInfo param) throws Exception {
        return new PersonalAccountAddOrderLoadRet(
                serviceUnitOfWork.getServiceBeanService().getFullAvailableServices(),
                serviceUnitOfWork.getServiceShopBeanService().getFullWorkingShop()
        );
    }

}
