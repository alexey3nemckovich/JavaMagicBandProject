package main.com.bsuir.autoservice.service.crud.impl.phone_operator;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.phone_operator;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class PhoneOperatorService extends AbstractServiceCrud<Integer, phone_operator> implements IPhoneOperatorService {

    @Inject
    public PhoneOperatorService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getPhoneOperatorDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(phone_operator bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;

            dependencies.add(new Dependency(
                    daoUnitOfWork.getPhoneDao().getTableName(),
                    "id_operator", id
            ));

            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
