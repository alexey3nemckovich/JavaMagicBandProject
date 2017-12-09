package main.com.bsuir.autoservice.service.crud.impl.product_type;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.product_type;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class ProductTypeService extends AbstractServiceCrud<Integer, product_type> implements IProductTypeService {

    @Inject
    public ProductTypeService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(product_type bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getProductDao().getTableName(),
                    "id_product_type", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
