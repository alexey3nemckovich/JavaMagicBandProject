package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountMakeOrderInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountMakeOrderRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountMakeOrderCommand implements ICommand<PersonalAccountMakeOrderInfo, PersonalAccountMakeOrderRet>{
    private final IUserSession session;
    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public PersonalAccountMakeOrderCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountMakeOrderRet execute(PersonalAccountMakeOrderInfo param) throws CommandException {
        try {
            return new PersonalAccountMakeOrderRet(serviceUnitOfWork.getOrderService().makeOrder(
                    session.getUserId(), param.getOrderServices()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
