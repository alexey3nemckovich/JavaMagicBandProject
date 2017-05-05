package main.com.bsuir.autoservice.dao.crud.discount_user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.discount_user;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DiscountUserDao extends AbstractDaoCrud<Integer, discount_user> implements IDiscountUserDao {

    @Inject
    public DiscountUserDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<discount_user> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<discount_user> result = new LinkedList<>();
        try {
            while (rs.next()) {
                discount_user bean = new discount_user();
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "discount_user";
}
