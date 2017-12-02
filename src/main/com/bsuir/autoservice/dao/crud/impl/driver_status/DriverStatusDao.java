package main.com.bsuir.autoservice.dao.crud.impl.driver_status;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.driver_status;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverStatusDao extends AbstractDaoCrud<Integer, driver_status> implements IDriverStatusDao{

    @Inject
    public DriverStatusDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<driver_status> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<driver_status> result = new LinkedList<>();
        try {
            while (rs.next()) {
                driver_status bean = new driver_status();

                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "driver_status";
}
