package main.com.bsuir.notepads.service.crud.impl.shop_product;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.shop_product;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class ShopProductService extends AbstractServiceCrud<Integer, shop_product> implements IShopProductService {

    @Inject
    public ShopProductService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getShopProductDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
