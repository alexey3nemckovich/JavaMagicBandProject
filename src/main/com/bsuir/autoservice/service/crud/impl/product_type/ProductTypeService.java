package main.com.bsuir.autoservice.service.crud.impl.product_type;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.product_type;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class ProductTypeService extends AbstractServiceCrud<Integer, product_type> implements IProductTypeService {

    @Inject
    public ProductTypeService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getProductTypeDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
