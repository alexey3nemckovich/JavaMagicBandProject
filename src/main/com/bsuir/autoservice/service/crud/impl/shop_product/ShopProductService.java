package main.com.bsuir.autoservice.service.crud.impl.shop_product;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.shop_product;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ShopProductService extends AbstractServiceCrud<Integer, shop_product> implements IShopProductService {

    @Inject
    public ShopProductService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getShopProductDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(shop_product bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getOrderedProductDao().getTableName(),
                    "id_shop_product", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
