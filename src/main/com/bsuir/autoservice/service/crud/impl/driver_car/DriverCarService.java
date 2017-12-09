package main.com.bsuir.autoservice.service.crud.impl.driver_car;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.driver_car;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class DriverCarService extends AbstractServiceCrud<Integer, driver_car> implements IDriverCarService {

    @Inject
    public DriverCarService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getDriverCarDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
