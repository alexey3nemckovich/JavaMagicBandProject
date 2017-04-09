package main.com.bsuir.autoservice.command.bean.page.main;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanMainPageInfo;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanMainPageCommand implements ICommand<BeanMainPageInfo> {

    @Inject
    public GetBeanMainPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanMainPageInfo execute(BeanMainPageInfo beanMainPageInfo) throws CommandException{
        try {
            beanMainPageInfo.dbBeanNames = serviceUnitOfWork.getBaseService().getListTableNames();
            return beanMainPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
