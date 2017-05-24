package main.com.bsuir.autoservice.dao.impl.discountuser;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.DiscountUser;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountUserDao extends AbstractCrudDao<NullType, DiscountUser> implements IDiscountUserDao {

    private static final String DISCOUNT_ID = "discount_id";
    private static final String USER_ID = "user_id";

    @Inject
    public DiscountUserDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<DiscountUser> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<DiscountUser>() {{
            while (rs.next()) {
                DiscountUser bean = new DiscountUser();
                bean.setDiscountId(rs.getInt(DISCOUNT_ID));
                bean.setUserId(rs.getInt(USER_ID));
                add(bean);
            }
        }};
    }
}
