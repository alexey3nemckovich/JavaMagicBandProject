package main.com.bsuir.autoservice.dao.crud.impl.spare_part;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.spare_part;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SparePartDao extends AbstractDaoCrud<Integer, spare_part> implements ISparePartDao{

    @Inject
    public SparePartDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<spare_part> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<spare_part> result = new LinkedList<>();
        try {
            while (rs.next()) {
                spare_part bean = new spare_part();
                bean.setId(rs.getInt("spare_part_id"));
                bean.setName(rs.getString("name"));
                bean.setAmountAvailable(rs.getInt("amount_available"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "spare_part";
}
