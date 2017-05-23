package main.com.bsuir.autoservice.dao.impl.share;

import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dto.ShareActiveDTO;

import java.util.List;

public interface IShareDao extends ICrudDao<Integer, Share> {
    List<ShareActiveDTO> getActive() throws DaoException;
}
