package main.com.bsuir.autoservice.service.crud.impl.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.user;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

public class UserService extends AbstractServiceCrud<Integer, user> implements IUserService{

    @Inject
    public UserService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<String> getDependencyTablesNames(){
        List<String> dependencyTableNames = new ArrayList<>();
        dependencyTableNames.add(daoUnitOfWork.getDiscountUserDao().getTableName());
        dependencyTableNames.add(daoUnitOfWork.getOrderDao().getTableName());
        dependencyTableNames.add(daoUnitOfWork.getStaffDao().getTableName());
        return dependencyTableNames;
    }

    @Override
    public Map<String, Dependency> readDependencies(user bean) throws ServiceException {
        try {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();
            dependencies.put(
                    daoUnitOfWork.getDiscountUserDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getDiscountUserDao(), "user_id", bean.getId())
            );
            dependencies.put(
                    daoUnitOfWork.getOrderDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getOrderDao(), "user_id", bean.getId())
            );
            dependencies.put(
                    daoUnitOfWork.getStaffDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getStaffDao(), "user_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
                throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
