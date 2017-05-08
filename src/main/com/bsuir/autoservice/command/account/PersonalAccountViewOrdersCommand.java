package main.com.bsuir.autoservice.command.account;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountViewOrdersInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrdersRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountViewOrdersCommand implements
        ICommand<PersonalAccountViewOrdersInfo, PersonalAccountViewOrdersRet>{
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IUserSession session;

    public PersonalAccountViewOrdersCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountViewOrdersRet execute(PersonalAccountViewOrdersInfo param) throws CommandException {
        try{
            return new PersonalAccountViewOrdersRet(serviceUnitOfWork.getOrderService().getUserOrders(
                    session.getUserId(), param.getCurrentPage(), param.getPageElementCount()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
