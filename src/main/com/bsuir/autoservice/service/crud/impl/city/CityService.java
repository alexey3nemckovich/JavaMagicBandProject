package main.com.bsuir.autoservice.service.crud.impl.city;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
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

    @Override
    public List<Dependency> readDependencies(city bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getAddressDao().getTableName(),
                    "id_city", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
