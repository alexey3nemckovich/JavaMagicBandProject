package main.com.bsuir.autoservice.service.crud.impl.address;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.address;


import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class AddressService extends AbstractServiceCrud<Integer, address> implements IAddressService {

    @Inject
    public AddressService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(address bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getShopDao().getTableName(),
                    "id_address", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getOrderDao().getTableName(),
                    "id_address", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
