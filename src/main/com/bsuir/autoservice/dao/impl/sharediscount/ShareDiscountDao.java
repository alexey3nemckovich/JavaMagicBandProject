package main.com.bsuir.autoservice.dao.impl.sharediscount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.ShareDiscount;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShareDiscountDao extends AbstractCrudDao<NullType, ShareDiscount> implements IShareDiscountDao {

    private static final String SHARE_ID = "share_id";
    private static final String DISCOUNT_ID = "discount_id";

    @Inject
    public ShareDiscountDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<ShareDiscount> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<ShareDiscount>() {{
            while (rs.next()) {
                ShareDiscount bean = new ShareDiscount();
                bean.setShareId(rs.getInt(SHARE_ID));
                bean.setDiscountId(rs.getInt(DISCOUNT_ID));
                add(bean);
            }
        }};
    }
}
