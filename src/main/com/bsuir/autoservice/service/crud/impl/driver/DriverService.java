package main.com.bsuir.autoservice.service.crud.impl.driver;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.driver;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class DriverService extends AbstractServiceCrud<Integer, driver> implements IDriverService {

    @Inject
    public DriverService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getDriverDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(driver bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getOrderDao().getTableName(),
                    "id_driver", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getDriverCarDao().getTableName(),
                    "id_driver", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
