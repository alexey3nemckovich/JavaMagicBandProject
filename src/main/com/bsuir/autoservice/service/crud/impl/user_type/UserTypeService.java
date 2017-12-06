package main.com.bsuir.autoservice.service.crud.impl.user_type;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.discount;
import main.com.bsuir.autoservice.bean.impl.backup.user;
import main.com.bsuir.autoservice.bean.impl.user_type;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.impl.backup.discount.IDiscountService;

import java.util.ArrayList;
import java.util.List;

public class UserTypeService extends AbstractServiceCrud<Integer, user_type> implements IUserTypeService {

    @Inject
    public UserService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(user bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;
            dependencies.add(new Dependency(
                    daoUnitOfWork.getDiscountUserDao().getTableName(),
                    "user_id", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getOrderDao().getTableName(),
                    "user_id", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getStaffDao().getTableName(),
                    "user_id", id
            ));
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
