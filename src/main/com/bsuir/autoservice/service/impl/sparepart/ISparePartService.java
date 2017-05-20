package main.com.bsuir.autoservice.service.impl.sparepart;

import main.com.bsuir.autoservice.bean.impl.SparePart;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface ISparePartService extends IService {
    List<SparePart> getView(int staffId, int sparePartGroup, int elementCount) throws ServiceException;
    boolean updateSparePart(SparePart sparePart) throws ServiceException;
}
