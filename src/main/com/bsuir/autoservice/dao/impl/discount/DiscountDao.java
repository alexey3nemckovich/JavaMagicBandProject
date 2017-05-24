package main.com.bsuir.autoservice.dao.impl.discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Discount;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountDao extends AbstractCrudDao<Integer, Discount> implements IDiscountDao {

    private static final String DISCOUNT_ID = "id";
    private static final String DISCOUNT_SERVICE_ID = "service_id";
    private static final String DISCOUNT_VALUE = "value";

    @Inject
    public DiscountDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Discount> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<Discount>() {{
            while (rs.next()) {
                Discount bean = new Discount();
                bean.setId(rs.getInt(DISCOUNT_ID));
                bean.setServiceId(rs.getInt(DISCOUNT_SERVICE_ID));
                bean.setValue(rs.getInt(DISCOUNT_VALUE));
                add(bean);
            }
        }};
    }
}
