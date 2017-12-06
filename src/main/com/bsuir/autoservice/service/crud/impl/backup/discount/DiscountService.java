package main.com.bsuir.autoservice.service.crud.impl.backup.discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.discount;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class DiscountService extends AbstractServiceCrud<Integer, discount> implements IDiscountService{

    @Inject
    public DiscountService(@Default IDaoUnitOfWork daoUnitOfWork){
        super(daoUnitOfWork.getDiscountDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(discount bean) throws ServiceException{
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;
            dependencies.add(new Dependency(
                    daoUnitOfWork.getShareDiscountDao().getTableName(),
                    "discount_id", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getDiscountUserDao().getTableName(),
                    "discount_id", id
            ));
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
