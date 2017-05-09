package main.com.bsuir.autoservice.command.mechanic;

import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.ChiefMechanicChangeSparePartInfo;
import main.com.bsuir.autoservice.command.ret.ChiefMechanicChangeSparePartRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class ChiefMechanicChangeSparePartCommand extends
        AbstractSessionCommand<ChiefMechanicChangeSparePartInfo, ChiefMechanicChangeSparePartRet>{
    public ChiefMechanicChangeSparePartCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected ChiefMechanicChangeSparePartRet executeImpl(ChiefMechanicChangeSparePartInfo param) throws Exception {
        return new ChiefMechanicChangeSparePartRet(serviceUnitOfWork.getSparePartService().updateSparePart(param.getSparePart()));
    }
}
