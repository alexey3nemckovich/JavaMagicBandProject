package main.com.bsuir.autoservice.service.crud.impl.discount_user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.discount_user;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

public class DiscountUserService extends AbstractServiceCrud<NullType, discount_user> implements IDiscountUserService{

    @Inject
    public DiscountUserService(@Default IDaoUnitOfWork daoUnitOfWork){
        super(daoUnitOfWork.getDiscountUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(discount_user bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
