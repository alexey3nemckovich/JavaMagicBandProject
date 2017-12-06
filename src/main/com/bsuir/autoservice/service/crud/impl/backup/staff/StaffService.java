package main.com.bsuir.autoservice.service.crud.impl.backup.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.staff;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

public class StaffService extends AbstractServiceCrud<Integer, staff> implements IStaffService{

    @Inject
    public StaffService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getStaffDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(staff bean) throws ServiceException{
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;
            dependencies.add(new Dependency(
                    daoUnitOfWork.getServiceShopDao().getTableName(),
                    "chief_id", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getNotificationDao().getTableName(),
                    "staff_id", id
            ));
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
