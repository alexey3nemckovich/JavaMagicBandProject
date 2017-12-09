package main.com.bsuir.notepads.service.crud.impl.ordered_product;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.ordered_product;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class OrderedProductService extends AbstractServiceCrud<Integer, ordered_product> implements IOrderedProductService {

    @Inject
    public OrderedProductService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderedProductDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
