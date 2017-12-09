package main.com.bsuir.autoservice.service.crud.impl.shop;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.shop;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class ShopService extends AbstractServiceCrud<Integer, shop> implements IShopService {

    @Inject
    public ShopService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getShopDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
