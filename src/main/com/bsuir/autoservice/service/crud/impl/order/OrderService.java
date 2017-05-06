package main.com.bsuir.autoservice.service.crud.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.order;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderService extends AbstractServiceCrud<Integer,order> implements IOrderService {

    @Inject
    public OrderService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(order bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            dependencies.addAll(
                    Arrays.asList(
                            getDependencyForTable(daoUnitOfWork.getOrderedServiceDao(), "order_id", bean.getId()),
                            getDependencyForTable(daoUnitOfWork.getOrderSparePartDao(), "order_id", bean.getId()),
                            getDependencyForTable(daoUnitOfWork.getNotificationDao(), "order_id", bean.getId())
                    )
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
