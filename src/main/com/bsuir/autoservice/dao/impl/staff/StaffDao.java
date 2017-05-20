package main.com.bsuir.autoservice.dao.impl.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StaffDao extends AbstractCrudDao<Integer, Staff> implements IStaffDao {

    @Inject
    public StaffDao(IDatabase db, ISql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Staff> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Staff> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setServiceShopId(rs.getInt("service_shop_id"));
                staff.setUserId(rs.getInt("user_id"));
                staff.setSpecialization(Staff.Specialization.valueOf(rs.getString("specialization")));
                result.add(staff);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
