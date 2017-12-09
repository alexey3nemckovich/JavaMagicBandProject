package main.com.bsuir.autoservice.service.crud.impl.driver_status;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.driver_status;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class DriverStatusService extends AbstractServiceCrud<Integer, driver_status> implements IDriverStatusService {

    @Inject
    public DriverStatusService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getDriverStatusDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
