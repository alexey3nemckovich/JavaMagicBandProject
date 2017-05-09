package main.com.bsuir.autoservice.service.crud.impl.share;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.share;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

public class ShareService extends AbstractServiceCrud<Integer, share> implements IShareService{

    @Inject
    public ShareService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getShareDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<Dependency> readDependencies(share bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;
            dependencies.add(new Dependency(
                    daoUnitOfWork.getShareDiscountDao().getTableName(),
                    "share_id", id
            ));
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
