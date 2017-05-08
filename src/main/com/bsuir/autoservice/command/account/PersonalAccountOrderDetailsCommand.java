package main.com.bsuir.autoservice.command.account;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountOrderDetailsInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderDetailsRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class PersonalAccountOrderDetailsCommand implements
        ICommand<PersonalAccountOrderDetailsInfo, PersonalAccountOrderDetailsRet>{
    private final IServiceUnitOfWork serviceUnifOfWork;
    private final IUserSession session;

    public PersonalAccountOrderDetailsCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnifOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public PersonalAccountOrderDetailsRet execute(PersonalAccountOrderDetailsInfo param) throws CommandException {
        try {
            return new PersonalAccountOrderDetailsRet(serviceUnifOfWork.getOrderService().getOrderServices(
                    session.getUserId(), param.getDetailOrderId()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
