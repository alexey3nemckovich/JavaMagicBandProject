package main.com.bsuir.notepads.dao.crud.impl.car_status;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.car_status;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CarStatusDao extends AbstractDaoCrud<Integer, car_status> implements ICarStatusDao{

    @Inject
    public CarStatusDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<car_status> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<car_status> result = new LinkedList<>();
        try {
            while (rs.next()) {
                car_status bean = new car_status();

                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "car_status";
}
