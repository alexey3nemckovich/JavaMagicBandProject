package main.com.bsuir.autoservice.dao.impl.share;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShareDao extends AbstractCrudDao<Integer, Share> implements IShareDao {

    @Inject
    public ShareDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Share> parseResultSet(ResultSet rs) throws DaoException {
        try {
            LinkedList<Share> result = new LinkedList<>();
            try {
                while (rs.next()) {
                    Share bean = new Share();
                    bean.setId(rs.getInt("id"));
                    bean.setDateStart(new SimpleDate(rs.getString("date_start")));
                    bean.setDateEnd(new SimpleDate(rs.getString("date_end")));
                    bean.setValue(rs.getInt("value"));
                    bean.setDescription(rs.getString("description"));
                    bean.setState(Share.State.valueOf(rs.getString("state")));
                    result.add(bean);
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
            return result;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }
}
