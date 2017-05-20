package main.com.bsuir.autoservice.service.impl.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;

public class StaffService implements IStaffService {

    @Inject
    public StaffService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
