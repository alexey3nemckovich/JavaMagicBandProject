package main.com.bsuir.autoservice.command.main;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.GeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.GeneralInformationRet;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GeneralInformationCommand implements ICommand<GeneralInformationInfo, GeneralInformationRet> {
    private final IServiceUnitOfWork serviceUnitOfWork;

    public GeneralInformationCommand(IServiceUnitOfWork serviceUnitOfWork) {
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public GeneralInformationRet execute(GeneralInformationInfo param) throws CommandException {
        try {
            //TODO: get only important information for page
            return new GeneralInformationRet(
                    serviceUnitOfWork.getServiceService().getAvailableServices(),
                    serviceUnitOfWork.getShareService().getActiveShares());
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
