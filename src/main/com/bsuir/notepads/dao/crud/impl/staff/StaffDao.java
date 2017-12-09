package main.com.bsuir.notepads.dao.crud.impl.staff;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.staff;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;
import main.com.bsuir.notepads.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class StaffDao extends AbstractDaoCrud<Integer, staff> implements IStaffDao{

    @Inject
    public StaffDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<staff> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<staff> result = new LinkedList<>();
        try {
            while (rs.next()) {
                staff bean = new staff();

                bean.setId(rs.getInt("id"));
                bean.setId_user(rs.getInt("id_user"));
                bean.setId_shop(rs.getInt("id_shop"));
                bean.setId_position(rs.getInt("id_position"));
                bean.setEmployment_time(new SimpleDate(rs.getString("employment_time")));

                result.add(bean);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "staff";
}