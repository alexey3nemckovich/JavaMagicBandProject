package main.com.bsuir.autoservice.dao.impl.sparepart;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.SparePart;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SparePartDao extends AbstractCrudDao<Integer, SparePart> implements ISparePartDao {

    @Inject
    public SparePartDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<SparePart> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<SparePart> result = new LinkedList<>();
        try {
            while (rs.next()) {
                SparePart bean = new SparePart();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setAmountAvailable(rs.getInt("amount_available"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
