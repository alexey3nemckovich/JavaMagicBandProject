package main.com.bsuir.autoservice.service.crud.impl.phone;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.phone;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class PhoneService extends AbstractServiceCrud<Integer, phone> implements IPhoneService {

    @Inject
    public PhoneService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getPhoneDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
