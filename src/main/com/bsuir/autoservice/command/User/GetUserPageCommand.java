package main.com.bsuir.autoservice.command.User;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.controller.bean.view.PageInfo;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import javax.servlet.ServletRequest;
import java.util.List;

public class GetUserPageCommand implements ICommand<List<User>>{
    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public GetUserPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public List<User> execute(Object data, ServletRequest request)
            throws CommandException {
        try {
            int totalBeanCount = serviceUnitOfWork.getUserService().readTotalCount();
            PageInfo pageInfo = (PageInfo)data;
            if(0 == pageInfo.countRecords) {
                pageInfo.countRecords = 3;
            }
            if(0 == pageInfo.page) {
                pageInfo.page = 1;
            }
            if(0 == totalBeanCount%pageInfo.countRecords){
                request.setAttribute("totalPages", totalBeanCount/pageInfo.countRecords);
            }else{
                request.setAttribute("totalPages", totalBeanCount/pageInfo.countRecords + 1);
            }
            request.setAttribute("page", pageInfo.page);
            int index = 0;
            if(1 != pageInfo.page){
                index = (pageInfo.page - 1)*pageInfo.countRecords;
            }
            return serviceUnitOfWork.getUserService().read(index,pageInfo.countRecords);
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
