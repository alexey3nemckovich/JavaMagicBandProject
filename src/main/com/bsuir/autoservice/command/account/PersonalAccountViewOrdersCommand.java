package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountViewOrdersInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrdersRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountViewOrdersCommand extends
        AbstractSessionCommand<PersonalAccountViewOrdersInfo, PersonalAccountViewOrdersRet>{

    @Inject
    public PersonalAccountViewOrdersCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountViewOrdersRet executeImpl(PersonalAccountViewOrdersInfo param) throws Exception {
        return new PersonalAccountViewOrdersRet(serviceUnitOfWork.getOrderService().getUserOrders(
                session.getUserId(), param.getCurrentPage(), param.getPageElementCount()));
    }
}
