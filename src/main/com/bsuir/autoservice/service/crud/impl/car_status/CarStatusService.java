package main.com.bsuir.autoservice.service.crud.impl.car_status;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CarStatusService extends AbstractServiceCrud<Integer, car_status> implements ICarStatusService {

    @Inject
    public CarStatusService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getCarStatusDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(car_status bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getCarDao().getTableName(),
                    "id_status", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
