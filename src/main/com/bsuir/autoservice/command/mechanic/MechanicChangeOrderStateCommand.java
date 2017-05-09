package main.com.bsuir.autoservice.command.mechanic;

import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.MechanicChangeOrderStateInfo;
import main.com.bsuir.autoservice.command.ret.MechanicChangeOrderStateRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class MechanicChangeOrderStateCommand extends
        AbstractSessionCommand<MechanicChangeOrderStateInfo, MechanicChangeOrderStateRet>{
    public MechanicChangeOrderStateCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected MechanicChangeOrderStateRet executeImpl(MechanicChangeOrderStateInfo param) throws Exception {
        return new MechanicChangeOrderStateRet(serviceUnitOfWork.getOrderService().changeOrderState(
                session.getUserId(), param.getOrderId(), param.getOrderState()
        ));
    }
}
