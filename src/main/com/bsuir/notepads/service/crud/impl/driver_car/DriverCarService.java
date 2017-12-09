package main.com.bsuir.notepads.service.crud.impl.driver_car;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.driver_car;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class DriverCarService extends AbstractServiceCrud<Integer, driver_car> implements IDriverCarService {

    @Inject
    public DriverCarService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getDriverCarDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
