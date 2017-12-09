package main.com.bsuir.notepads.dao.crud.impl.driver_car;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.driver_car;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverCarDao extends AbstractDaoCrud<NullType, driver_car> implements IDriverCarDao{

    @Inject
    public DriverCarDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<driver_car> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<driver_car> result = new LinkedList<>();
        try {
            while (rs.next()) {
                driver_car bean = new driver_car();

                bean.setId_driver(rs.getInt("id_driver"));
                bean.setId_car(rs.getInt("id_car"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "driver_car";
}
