package main.com.bsuir.autoservice.service.crud.impl.car;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CarService extends AbstractServiceCrud<Integer, car> implements ICarService {

    @Inject
    public CarService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getCarDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
