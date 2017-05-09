package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanMainPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.ArrayList;
import java.util.List;

public class GetBeanMainPageCommand implements ICommand<BeanMainPageInfo> {

    @Inject
    public GetBeanMainPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanMainPageInfo execute(BeanMainPageInfo beanMainPageInfo) throws CommandException{
        try {
            List<String> displayingTablesNames = new ArrayList<>();
            List<IServiceCrud> allTablesServices = serviceUnitOfWork.getAllTablesServices();
            for(IServiceCrud serviceCrud : allTablesServices){
                if(serviceCrud.readDependencies().size() != 0){
                    displayingTablesNames.add(serviceCrud.getTableName());
                }
            }
            beanMainPageInfo.dbBeanNames = displayingTablesNames;
            return beanMainPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
