package main.com.bsuir.autoservice.service.crud.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.order;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

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
            Integer id = bean != null ? bean.getId() : null;
            dependencies.add(new Dependency(
                    daoUnitOfWork.getOrderedServiceDao().getTableName(),
                    "order_id", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getOrderSparePartDao().getTableName(),
                    "order_id", id
            ));
            dependencies.add(new Dependency(
                    daoUnitOfWork.getNotificationDao().getTableName(),
                    "order_id", id
            ));
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
