package main.com.bsuir.autoservice.service.crud.impl.product;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.product;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class ProductService extends AbstractServiceCrud<Integer, product> implements IProductService {

    @Inject
    public ProductService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getProductDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
