package main.com.bsuir.autoservice.service.crud.impl.spare_part;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.spare_part;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.*;

public class SparePartService extends AbstractServiceCrud<Integer, spare_part> implements ISparePartService{

    @Inject
    public SparePartService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getSparePartDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<String> getDependencyTablesNames(){
        List<String> dependencyTableNames = new ArrayList<>();
        dependencyTableNames.add(daoUnitOfWork.getSparePartDao().getTableName());
        return dependencyTableNames;
    }

    @Override
    public Map<String, Dependency> readDependencies(spare_part bean) throws ServiceException {
        try {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();
            dependencies.put(
                    daoUnitOfWork.getSparePartDao().getTableName(),
                    getDependencyForTable(daoUnitOfWork.getSparePartDao(), "spare_part_id", bean.getId())
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
