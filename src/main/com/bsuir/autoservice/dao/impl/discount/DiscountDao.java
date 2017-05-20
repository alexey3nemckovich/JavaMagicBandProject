package main.com.bsuir.autoservice.dao.crud.impl.discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.discount;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DiscountDao extends AbstractDaoCrud<Integer, discount> implements IDiscountDao {

    @Inject
    public DiscountDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<discount> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<discount> result = new LinkedList<>();
        try {
            while (rs.next()) {
                discount bean = new discount();
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

    private final String tableName = "discount";
}
