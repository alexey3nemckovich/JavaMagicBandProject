package main.com.bsuir.notepads.service.crud.impl.car;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.car;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class CarService extends AbstractServiceCrud<Integer, car> implements ICarService {

    @Inject
    public CarService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getCarDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
