package main.com.bsuir.autoservice.service.crud.impl.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.staff;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            dependencies.addAll(
                    Arrays.asList(
                            getDependencyForTable(daoUnitOfWork.getServiceShopDao(), "chief_id", bean.getId()),
                            getDependencyForTable(daoUnitOfWork.getNotificationDao(), "staff_id", bean.getId())
                    )
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
