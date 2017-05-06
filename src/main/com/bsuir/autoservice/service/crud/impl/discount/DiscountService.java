package main.com.bsuir.autoservice.service.crud.impl.discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.discount;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiscountService extends AbstractServiceCrud<Integer, discount> implements IDiscountService{

    @Inject
    public DiscountService(@Default IDaoUnitOfWork daoUnitOfWork){
        super(daoUnitOfWork.getDiscountDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<String> getDependencyTablesNames(){
        List<String> dependencyTableNames = new ArrayList<>();
        dependencyTableNames.add(daoUnitOfWork.getShareDiscountDao().getTableName());
        dependencyTableNames.add(daoUnitOfWork.getDiscountUserDao().getTableName());
        return dependencyTableNames;
    }

    @Override
    public Map<String, Dependency> readDependencies(discount bean) throws ServiceException{
        try {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();
            dependencies.put(
                    daoUnitOfWork.getShareDiscountDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getShareDiscountDao(), "discount_id", bean.getId())
            );
            dependencies.put(
                    daoUnitOfWork.getDiscountUserDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getDiscountUserDao(),"discount_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
