package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountMakeOrderInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountMakeOrderRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountMakeOrderCommand extends
        AbstractSessionCommand<PersonalAccountMakeOrderInfo, PersonalAccountMakeOrderRet> {

    @Inject
    public PersonalAccountMakeOrderCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountMakeOrderRet executeImpl(PersonalAccountMakeOrderInfo param) throws Exception {
        return new PersonalAccountMakeOrderRet(serviceUnitOfWork.getOrderService().makeOrder(
                session.getUserId(), param.getOrderServices()));
    }
}
