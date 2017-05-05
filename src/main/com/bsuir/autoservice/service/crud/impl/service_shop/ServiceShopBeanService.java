package main.com.bsuir.autoservice.service.crud.impl.service_shop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.service_shop;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceShopBeanService extends AbstractServiceCrud<Integer, service_shop> implements IServiceShopBeanService{

    @Inject
    public ServiceShopBeanService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getServiceShopDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(service_shop bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            dependencies.addAll(
                    Arrays.asList(
                            getDependencyForTable(daoUnitOfWork.getOrderDao(), "service_shop_id", bean.getId()),
                            getDependencyForTable(daoUnitOfWork.getStaffDao(), "service_shop_id", bean.getId())
                    )
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
