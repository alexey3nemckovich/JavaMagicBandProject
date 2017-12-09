package main.com.bsuir.autoservice.service.crud.impl.address;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.address;


import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class AddressService extends AbstractServiceCrud<Integer, address> implements IAddressService {

    @Inject
    public AddressService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getAddressDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
