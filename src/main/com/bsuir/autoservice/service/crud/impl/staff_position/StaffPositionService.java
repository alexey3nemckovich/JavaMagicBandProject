package main.com.bsuir.autoservice.service.crud.impl.staff_position;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.staff_position;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class StaffPositionService extends AbstractServiceCrud<Integer, staff_position> implements IStaffPositionService {

    @Inject
    public StaffPositionService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getStaffPositionDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
