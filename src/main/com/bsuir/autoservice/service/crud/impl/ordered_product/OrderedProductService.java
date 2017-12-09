package main.com.bsuir.autoservice.service.crud.impl.ordered_product;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.ordered_product;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class OrderedProductService extends AbstractServiceCrud<Integer, ordered_product> implements IOrderedProductService {

    @Inject
    public OrderedProductService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderedProductDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
