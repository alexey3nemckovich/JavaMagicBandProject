package main.com.bsuir.autoservice.service.crud.impl.driver_status;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.driver_status;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class DriverStatusService extends AbstractServiceCrud<Integer, driver_status> implements IDriverStatusService {

    @Inject
    public DriverStatusService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getDriverStatusDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(driver_status bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getDriverDao().getTableName(),
                    "id_driver_status", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
