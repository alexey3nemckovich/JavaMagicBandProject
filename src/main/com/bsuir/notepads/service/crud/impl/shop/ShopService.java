package main.com.bsuir.notepads.service.crud.impl.shop;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.shop;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class ShopService extends AbstractServiceCrud<Integer, shop> implements IShopService {

    @Inject
    public ShopService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getShopDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
