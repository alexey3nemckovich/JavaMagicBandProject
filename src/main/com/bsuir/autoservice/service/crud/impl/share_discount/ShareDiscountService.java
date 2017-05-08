package main.com.bsuir.autoservice.service.crud.impl.share_discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.share_discount;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

public class ShareDiscountService extends AbstractServiceCrud<NullType, share_discount> implements IShareDiscountService{

    @Inject
    public ShareDiscountService(@Default IDaoUnitOfWork daoUnitOfWork){
        super(daoUnitOfWork.getShareDiscountDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(share_discount bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
