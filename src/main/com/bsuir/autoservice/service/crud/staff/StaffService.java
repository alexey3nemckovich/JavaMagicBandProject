package main.com.bsuir.autoservice.service.crud.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.staff;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.IStaffService;

public class StaffService extends AbstractServiceCrud<Integer, staff> implements IStaffService {
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public StaffService(IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getStaffDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }
}
