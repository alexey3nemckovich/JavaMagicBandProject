package main.com.bsuir.autoservice.command.mechanic;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.MechanicViewOrdersInfo;
import main.com.bsuir.autoservice.command.ret.MechanicViewOrdersRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class MechanicViewOrdersCommand implements ICommand<MechanicViewOrdersInfo, MechanicViewOrdersRet>{
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IUserSession session;

    public MechanicViewOrdersCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public MechanicViewOrdersRet execute(MechanicViewOrdersInfo param) throws CommandException {
        try{
            return new MechanicViewOrdersRet(serviceUnitOfWork.getOrderService().getServiceShopOrders(
                    session.getStaffServiceShopId(), param.getOrderSortType(), param.getOrderPage(),
                    param.getOrderCount()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
