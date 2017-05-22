package main.com.bsuir.autoservice.dao.impl.discountuser;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.DiscountUser;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DiscountUserDao extends AbstractCrudDao<NullType, DiscountUser> implements IDiscountUserDao {

    @Inject
    public DiscountUserDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<DiscountUser> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<DiscountUser> result = new LinkedList<>();
        try {
            while (rs.next()) {
                DiscountUser bean = new DiscountUser();
                bean.setDiscountId(rs.getInt("discount_id"));
                bean.setUserId(rs.getInt("user_id"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
