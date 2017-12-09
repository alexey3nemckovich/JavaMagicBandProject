package main.com.bsuir.notepads.service.crud.impl.address;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.address;


import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class AddressService extends AbstractServiceCrud<Integer, address> implements IAddressService {

    @Inject
    public AddressService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getAddressDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
