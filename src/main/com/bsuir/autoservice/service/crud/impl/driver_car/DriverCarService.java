package main.com.bsuir.autoservice.service.crud.impl.driver_car;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.driver_car;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class DriverCarService extends AbstractServiceCrud<Integer, driver_car> implements IDriverCarService {

    @Inject
    public DriverCarService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(driver_car bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
