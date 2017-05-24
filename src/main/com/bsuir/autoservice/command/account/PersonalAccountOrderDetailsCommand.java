package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountOrderDetailsInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderDetailsRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountOrderDetailsCommand
        extends AbstractSessionCommand<PersonalAccountOrderDetailsInfo, PersonalAccountOrderDetailsRet> {

    @Inject
    public PersonalAccountOrderDetailsCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountOrderDetailsRet executeImpl(PersonalAccountOrderDetailsInfo param) throws Exception {
        return new PersonalAccountOrderDetailsRet(serviceUnitOfWork.getOrderService().getOrderServices(
                session.getUserId(), param.getDetailOrderId()));
    }
}
