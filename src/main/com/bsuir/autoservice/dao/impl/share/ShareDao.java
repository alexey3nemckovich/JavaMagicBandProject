package main.com.bsuir.autoservice.dao.impl.share;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;
import main.com.bsuir.autoservice.dto.ShareActiveDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShareDao extends AbstractCrudDao<Integer, Share> implements IShareDao {

    private static final String SHARE_ID = "id";
    private static final String SHARE_DATE_START = "date_start";
    private static final String SHARE_DATE_END = "date_end";
    private static final String SHARE_VALUE = "value";
    private static final String SHARE_DESCRIPTION = "description";
    private static final String SHARE_STATE = "state";

    @Inject
    public ShareDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Share> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<Share>() {{
            while (rs.next()) {
                Share bean = new Share();
                bean.setId(rs.getInt(SHARE_ID));
                bean.setDateStart(rs.getDate(SHARE_DATE_START));
                bean.setDateEnd(rs.getDate(SHARE_DATE_END));
                bean.setValue(rs.getInt(SHARE_VALUE));
                bean.setDescription(rs.getString(SHARE_DESCRIPTION));
                bean.setState(Share.State.valueOf(rs.getString(SHARE_STATE)));
                add(bean);
            }
        }};
    }

    @Override
    public List<ShareActiveDTO> getActive() throws DaoException {
        //TODO: write active
        return new ArrayList<ShareActiveDTO>();
    }
}
