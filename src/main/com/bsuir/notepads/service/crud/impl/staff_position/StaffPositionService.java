package main.com.bsuir.notepads.service.crud.impl.staff_position;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.staff_position;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class StaffPositionService extends AbstractServiceCrud<Integer, staff_position> implements IStaffPositionService {

    @Inject
    public StaffPositionService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getStaffPositionDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
