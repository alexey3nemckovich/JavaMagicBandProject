package main.com.bsuir.autoservice.dao.impl.sharediscount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.ShareDiscount;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.ISql;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShareDiscountDao extends AbstractCrudDao<NullType, ShareDiscount> implements IShareDiscountDao{

    @Inject
    public ShareDiscountDao(IDatabase db, ISql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);

    }

    @Override
    public List<ShareDiscount> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<ShareDiscount> result = new LinkedList<>();
        try {
            while (rs.next()) {
                ShareDiscount bean = new ShareDiscount();
                bean.setShareId(rs.getInt("share_id"));
                bean.setDiscountId(rs.getInt("discount_id"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
