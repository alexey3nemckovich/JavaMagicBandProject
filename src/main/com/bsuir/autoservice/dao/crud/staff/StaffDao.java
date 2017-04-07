package main.com.bsuir.autoservice.dao.crud.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Staff;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StaffDao extends AbstractDaoCrud<Staff, Integer> implements IStaffDao{
    private static final String tableName = "staff";
    private static final String primaryKeyName = "id";

    @Inject
    public StaffDao(IDatabase db, ISql sql){super(db, sql);}

    @Override
    public String getTableNameImpl() {
        return tableName;
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
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
