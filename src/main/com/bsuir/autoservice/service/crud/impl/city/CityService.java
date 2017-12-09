package main.com.bsuir.autoservice.service.crud.impl.city;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class CityService extends AbstractServiceCrud<Integer, city> implements ICityService {

    @Inject
    public CityService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getCityDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
