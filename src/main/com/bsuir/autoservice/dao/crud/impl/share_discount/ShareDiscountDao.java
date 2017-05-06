package main.com.bsuir.autoservice.dao.crud.impl.share_discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.share_discount;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShareDiscountDao extends AbstractDaoCrud<Integer, share_discount> implements IShareDiscountDao{

    @Inject
    public ShareDiscountDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<share_discount> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<share_discount> result = new LinkedList<>();
        try {
            while (rs.next()) {
                share_discount bean = new share_discount();
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "share_discount";
}
