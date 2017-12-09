package main.com.bsuir.notepads.service.crud.impl.city;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.city;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class CityService extends AbstractServiceCrud<Integer, city> implements ICityService {

    @Inject
    public CityService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getCityDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
