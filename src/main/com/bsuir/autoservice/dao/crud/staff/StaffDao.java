package main.com.bsuir.autoservice.dao.crud.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.staff;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StaffDao extends AbstractDaoCrud<staff, Integer> implements IStaffDao{

    @Inject
    public StaffDao(IDatabase db, ISql sql){super(db, sql);}

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<staff> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<staff> result = new LinkedList<>();
        try {
            while (rs.next()) {
                staff staff = new staff();
                staff.setId(rs.getInt("id"));
                staff.setServiceShopId(rs.getInt("service_shop_id"));
                staff.setUserId(rs.getInt("user_id"));
                staff.setSpecialization(main.com.bsuir.autoservice.bean.staff.Specialization.valueOf(rs.getString("specialization")));
                result.add(staff);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private static final String tableName = "staff";
}
