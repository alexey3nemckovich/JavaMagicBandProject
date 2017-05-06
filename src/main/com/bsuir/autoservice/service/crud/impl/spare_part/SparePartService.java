package main.com.bsuir.autoservice.service.crud.impl.spare_part;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.spare_part;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            dependencies.addAll(
                    Arrays.asList(
                        getDependencyForTable(daoUnitOfWork.getSparePartDao(), "spare_part_id", bean.getId())
                    )
            );
            return dependencies;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
