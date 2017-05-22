package main.com.bsuir.autoservice.command.crud;

import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;

public abstract class AbstractGetBeanPageCommand {

    protected void readPage(BeanViewPageInfo beanViewPageInfo, ICrudService serviceCrud)
        throws ServiceException {
        beanViewPageInfo.totalPagesCount = getTotalPagesCount(beanViewPageInfo.countRecords,
                serviceCrud.readTotalCount(beanViewPageInfo.tableName));
        if (beanViewPageInfo.totalPagesCount < beanViewPageInfo.page){
            if(0 == beanViewPageInfo.totalPagesCount){
                beanViewPageInfo.page = 1;
            }else{
                beanViewPageInfo.page = beanViewPageInfo.totalPagesCount;
            }
        }
        int index = getFirstBeanIndex(beanViewPageInfo);
        beanViewPageInfo.beans = serviceCrud.read(beanViewPageInfo.tableName, index, beanViewPageInfo.countRecords);
    }

    protected int getTotalPagesCount(int countRecords, int totalBeanCount)
        throws ServiceException{
        int totalPagesCount = totalBeanCount / countRecords;
        if (0 != totalBeanCount % countRecords) {
            totalPagesCount++;
        }
        return totalPagesCount;
    }

    protected int getFirstBeanIndex(BeanViewPageInfo beanViewPageInfo){
        int index = 0;
        if (1 != beanViewPageInfo.page) {
            index = (beanViewPageInfo.page - 1) * beanViewPageInfo.countRecords;
        }
        return index;
    }
}
