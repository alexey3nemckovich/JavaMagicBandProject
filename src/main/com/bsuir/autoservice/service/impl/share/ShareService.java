package main.com.bsuir.autoservice.service.impl.share;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.dto.ShareActiveDTO;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public class ShareService implements IShareService {

    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public ShareService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public List<ShareActiveDTO> getActiveShares() throws ServiceException {
        try {
            return daoUnitOfWork.getShareDao().getActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Share> getActiveAccountShares(Integer userId) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
