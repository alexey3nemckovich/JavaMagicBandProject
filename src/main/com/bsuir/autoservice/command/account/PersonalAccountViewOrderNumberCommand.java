package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.NoInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrderNumberRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountViewOrderNumberCommand extends AbstractSessionCommand<NoInfo, PersonalAccountViewOrderNumberRet> {

    @Inject
    public PersonalAccountViewOrderNumberCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountViewOrderNumberRet executeImpl(NoInfo param) throws Exception {
        return new PersonalAccountViewOrderNumberRet(serviceUnitOfWork.getOrderService().getAllNumber());
    }
}
