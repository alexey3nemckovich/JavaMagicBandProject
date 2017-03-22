package main.com.bsuir.autoservice.service.impl.userService.impl;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.dao.DaoController;
import main.com.bsuir.autoservice.service.impl.serviceCrud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.impl.userService.IUserService;

public class UserService extends AbstractServiceCrud<Integer,User> implements IUserService {
    public UserService(DaoController daoController) {
        super(daoController);
    }
}
