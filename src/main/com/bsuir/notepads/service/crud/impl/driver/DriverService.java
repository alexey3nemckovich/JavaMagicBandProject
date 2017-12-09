package main.com.bsuir.notepads.service.crud.impl.driver;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.driver;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class DriverService extends AbstractServiceCrud<Integer, driver> implements IDriverService {

    @Inject
    public DriverService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getDriverDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
