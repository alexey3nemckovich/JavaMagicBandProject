package main.com.bsuir.notepads.service.crud.impl.driver_status;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.driver_status;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class DriverStatusService extends AbstractServiceCrud<Integer, driver_status> implements IDriverStatusService {

    @Inject
    public DriverStatusService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getDriverStatusDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
