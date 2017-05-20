package main.com.bsuir.autoservice.command.mechanic;

import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.ChiefMechanicViewSparePartInfo;
import main.com.bsuir.autoservice.command.ret.ChiefMechanicViewSparePartRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class ChiefMechanicViewSparePartCommand extends
        AbstractSessionCommand<ChiefMechanicViewSparePartInfo, ChiefMechanicViewSparePartRet>{
    public ChiefMechanicViewSparePartCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected ChiefMechanicViewSparePartRet executeImpl(ChiefMechanicViewSparePartInfo param) throws Exception {
        return new ChiefMechanicViewSparePartRet(serviceUnitOfWork.getSparePartService().getView(session.getUserId(),
                param.getPageNumber(), param.getPageElementCount()));
    }
}
