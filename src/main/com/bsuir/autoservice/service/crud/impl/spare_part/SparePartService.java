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
    public List<Dependency> readDependencies(spare_part bean) throws ServiceException {
        try {
            List<Dependency> dependencies = new ArrayList<>();
            Integer id = bean != null ? bean.getId() : null;
            dependencies.add(new Dependency(
                    daoUnitOfWork.getSparePartDao().getTableName(),
                    "spare_part_id", id
            ));
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
