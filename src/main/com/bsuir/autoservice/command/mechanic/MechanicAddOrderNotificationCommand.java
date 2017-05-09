package main.com.bsuir.autoservice.command.mechanic;

import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.MechanicAddOrderNotificationInfo;
import main.com.bsuir.autoservice.command.ret.MechanicAddOrderNotificationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class MechanicAddOrderNotificationCommand extends
        AbstractSessionCommand<MechanicAddOrderNotificationInfo, MechanicAddOrderNotificationRet>{
    public MechanicAddOrderNotificationCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected MechanicAddOrderNotificationRet executeImpl(MechanicAddOrderNotificationInfo param) throws Exception {
        return new MechanicAddOrderNotificationRet(serviceUnitOfWork.getOrderService().addOrderNotification(
                session.getUserId(), param.getOrderId(), param.getNotificationMessage()
        ));
    }
}
