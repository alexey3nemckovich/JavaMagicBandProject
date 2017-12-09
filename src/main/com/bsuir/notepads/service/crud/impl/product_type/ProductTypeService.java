package main.com.bsuir.notepads.service.crud.impl.product_type;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.product_type;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class ProductTypeService extends AbstractServiceCrud<Integer, product_type> implements IProductTypeService {

    @Inject
    public ProductTypeService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getProductTypeDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
