package main.com.bsuir.autoservice.service.crud.impl.service_shop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.service_shop;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

public class ServiceShopBeanService extends AbstractServiceCrud<Integer, service_shop> implements IServiceShopBeanService{

    @Inject
    public ServiceShopBeanService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getServiceShopDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<String> getDependencyTablesNames(){
        List<String> dependencyTableNames = new ArrayList<>();
        dependencyTableNames.add(daoUnitOfWork.getOrderDao().getTableName());
        dependencyTableNames.add(daoUnitOfWork.getStaffDao().getTableName());
        return dependencyTableNames;
    }

    @Override
    public Map<String, Dependency> readDependencies(service_shop bean) throws ServiceException {
        try {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();
            dependencies.put(
                    daoUnitOfWork.getOrderDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getOrderDao(), "service_shop_id", bean.getId())
            );
            dependencies.put(
                    daoUnitOfWork.getStaffDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getStaffDao(), "service_shop_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
