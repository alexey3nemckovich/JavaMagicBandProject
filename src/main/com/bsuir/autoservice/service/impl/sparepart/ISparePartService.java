package main.com.bsuir.autoservice.service.impl;

import main.com.bsuir.autoservice.bean.impl.spare_part;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface ISparePartService extends IServiceCrud<Integer, spare_part> {
    List<spare_part> getView(int staffId, int sparePartGroup, int elementCount) throws ServiceException;
    boolean updateSparePart(spare_part sparePart) throws ServiceException;
}
