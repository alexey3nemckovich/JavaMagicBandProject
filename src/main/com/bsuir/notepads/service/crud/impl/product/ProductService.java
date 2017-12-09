package main.com.bsuir.notepads.service.crud.impl.product;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.product;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class ProductService extends AbstractServiceCrud<Integer, product> implements IProductService {

    @Inject
    public ProductService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getProductDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
