package main.com.bsuir.autoservice.service.crud.impl.order_spare_part;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.order_spare_part;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

public class OrderSparePartService extends AbstractServiceCrud<NullType, order_spare_part> implements IOrderSparePartService{

    @Inject
    public OrderSparePartService(@Default IDaoUnitOfWork daoUnitOfWork){
        super(daoUnitOfWork.getOrderSparePartDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(order_spare_part bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
