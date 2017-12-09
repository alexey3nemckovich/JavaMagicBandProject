package main.com.bsuir.notepads.dao.crud.impl.car;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.car;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;
import main.com.bsuir.notepads.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class CarDao extends AbstractDaoCrud<Integer, car> implements ICarDao{

    @Inject
    public CarDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<car> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<car> result = new LinkedList<>();
        try {
            while (rs.next()) {
                car bean = new car();

                bean.setId(rs.getInt("id"));
                bean.setId_status(rs.getInt("id_status"));
                bean.setLast_inspection(new SimpleDate(rs.getString("last_inspection")));
                bean.setRegister_number(rs.getString("register_number"));
                bean.setRegister_region(rs.getString("register_region"));
                bean.setFull_register_number(rs.getString("full_register_number"));

                result.add(bean);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "car";
}
