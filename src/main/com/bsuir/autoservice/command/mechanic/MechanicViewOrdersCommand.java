package main.com.bsuir.autoservice.command.mechanic;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.MechanicViewOrdersInfo;
import main.com.bsuir.autoservice.command.ret.MechanicViewOrdersRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class MechanicViewOrdersCommand extends AbstractSessionCommand<MechanicViewOrdersInfo, MechanicViewOrdersRet>{

    @Inject
    public MechanicViewOrdersCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected MechanicViewOrdersRet executeImpl(MechanicViewOrdersInfo param) throws Exception {
        return new MechanicViewOrdersRet(serviceUnitOfWork.getOrderService().getServiceShopOrders(
                session.getStaffServiceShopId(), param.getOrderSortType(), param.getOrderPage(),
                param.getOrderCount()));
    }
}
