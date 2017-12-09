package main.com.bsuir.notepads.dao.crud.impl.staff_position;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.staff_position;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StaffPositionDao extends AbstractDaoCrud<Integer, staff_position> implements IStaffPositionDao{

    @Inject
    public StaffPositionDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<staff_position> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<staff_position> result = new LinkedList<>();
        try {
            while (rs.next()) {
                staff_position bean = new staff_position();

                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "staff_position";
}