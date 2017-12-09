package main.com.bsuir.notepads.service.crud.impl.car_status;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.car_status;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class CarStatusService extends AbstractServiceCrud<Integer, car_status> implements ICarStatusService {

    @Inject
    public CarStatusService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getCarStatusDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
