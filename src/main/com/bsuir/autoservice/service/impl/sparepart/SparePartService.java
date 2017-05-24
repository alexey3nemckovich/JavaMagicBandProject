package main.com.bsuir.autoservice.service.impl.sparepart;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.SparePart;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class SparePartService implements ISparePartService {

    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public SparePartService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<SparePart> getView(int staffId, int sparePartGroup, int elementCount) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateSparePart(SparePart sparePart) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SparePart> getAvailableSparePartList() throws ServiceException{
        try {
            List<SparePart> sparePartList = daoUnitOfWork.getSparePartDao().getAll();
            List<SparePart> availableSparePartList = new ArrayList<>();
            for(SparePart sparePart : sparePartList){
                if(sparePart.getAmountAvailable() > 0){
                    availableSparePartList.add(sparePart);
                }
            }
            return availableSparePartList;
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
