package main.com.bsuir.autoservice.service.crud.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Staff;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;

public class StaffService extends AbstractServiceCrud<Integer, Staff> implements IStaffService{
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public StaffService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getStaffDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }
}
