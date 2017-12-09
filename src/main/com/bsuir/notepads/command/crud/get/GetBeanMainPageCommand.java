package main.com.bsuir.notepads.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.command.ICommand;
import main.com.bsuir.notepads.command.exception.CommandException;
import main.com.bsuir.notepads.command.param.BeanMainPageInfo;
import main.com.bsuir.notepads.service.crud.IServiceCrud;
import main.com.bsuir.notepads.service.unitOfWork.IServiceUnitOfWork;

import java.util.ArrayList;
import java.util.List;

public class    GetBeanMainPageCommand implements ICommand<BeanMainPageInfo> {

    @Inject
    public GetBeanMainPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanMainPageInfo execute(BeanMainPageInfo beanMainPageInfo) throws CommandException{
        try {
            List<String> displayingTablesNames = new ArrayList<>();
            List<IServiceCrud> allTablesServices = serviceUnitOfWork.getAllTablesServices();

            List<String> names = new ArrayList<>();
            for(IServiceCrud crud : allTablesServices){
                names.add(crud.getTableName());
            }
            beanMainPageInfo.dbBeanNames = names;

            return beanMainPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
