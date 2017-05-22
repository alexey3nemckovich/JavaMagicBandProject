package main.com.bsuir.autoservice.dao.impl.discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Discount;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DiscountDao extends AbstractCrudDao<Integer, Discount> implements IDiscountDao {

    @Inject
    public DiscountDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Discount> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Discount> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Discount bean = new Discount();
                bean.setId(rs.getInt("id"));
                bean.setServiceId(rs.getInt("service_id"));
                bean.setValue(rs.getInt("value"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
