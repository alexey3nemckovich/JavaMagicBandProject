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
    public List<String> getDependencyTablesNames(){
        List<String> dependencyTableNames = new ArrayList<>();
        dependencyTableNames.add(daoUnitOfWork.getShareDiscountDao().getTableName());
        return dependencyTableNames;
    }

    @Override
    public Map<String, Dependency> readDependencies(share bean) throws ServiceException {
        try {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();
            dependencies.put(
                    daoUnitOfWork.getShareDiscountDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getShareDiscountDao(),"share_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
