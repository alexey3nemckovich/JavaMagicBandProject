package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.impl.userService.IServiceUserService;

public interface IServiceUnitOfWork {
   IServiceUserService getUserService();
}
