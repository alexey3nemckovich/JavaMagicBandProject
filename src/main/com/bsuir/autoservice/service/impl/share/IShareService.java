package main.com.bsuir.autoservice.service.impl.share;

import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.dto.ShareActiveDTO;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IShareService extends IService{
    List<ShareActiveDTO> getActiveSharesDTO() throws ServiceException;
    List<Share> getActiveShares() throws ServiceException;
    List<Share> getActiveAccountShares(Integer userId) throws ServiceException;
}
