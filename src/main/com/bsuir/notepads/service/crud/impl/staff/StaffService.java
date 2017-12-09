package main.com.bsuir.notepads.service.crud.impl.staff;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.staff;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class StaffService extends AbstractServiceCrud<Integer, staff> implements IStaffService {

    @Inject
    public StaffService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getStaffDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
