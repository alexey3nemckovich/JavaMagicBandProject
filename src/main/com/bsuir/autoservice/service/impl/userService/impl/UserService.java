package main.com.bsuir.autoservice.service.impl.userService.impl;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.dao.DaoController;
import main.com.bsuir.autoservice.service.impl.serviceCrud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.impl.userService.IServiceUserService;

public class UserService extends AbstractServiceCrud<Integer,User> implements IServiceUserService {
    UserService(DaoController daoController) {
        super(daoController);
    }
}
