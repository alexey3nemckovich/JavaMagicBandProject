package main.com.bsuir.notepads.dao.crud.impl.driver;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.driver;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverDao extends AbstractDaoCrud<Integer, driver> implements IDriverDao{

    @Inject
    public DriverDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<driver> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<driver> result = new LinkedList<>();
        try {
            while (rs.next()) {
                driver bean = new driver();

                bean.setId(rs.getInt("id"));
                bean.setId_driver_status(rs.getInt("id_driver_status"));
                bean.setId_staff(rs.getInt("id_staff"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "driver";
}
