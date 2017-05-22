package main.com.bsuir.autoservice.dao.impl.staff;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StaffDao extends AbstractCrudDao<Integer, Staff> implements IStaffDao {

    private static final String STAFF_ID = "id";
    private static final String STAFF_SERVICE_SHOP_ID = "service_shop_id";
    private static final String STAFF_USER_ID = "user_id";
    private static final String STAFF_SPECIALIZATION = "specialization";

    @Inject
    public StaffDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Staff> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Staff> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt(STAFF_ID));
                staff.setServiceShopId(rs.getInt(STAFF_SERVICE_SHOP_ID));
                staff.setUserId(rs.getInt(STAFF_USER_ID));
                staff.setSpecialization(getStaffSpecialization(rs, STAFF_SPECIALIZATION));
                result.add(staff);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private Staff.Specialization getStaffSpecialization(ResultSet rs, String specialization_named) throws SQLException {
        return Staff.Specialization.valueOf(rs.getString(specialization_named));
    }

    @Override
    public Staff.Specialization getSpecialization(int idLogin) throws DaoException {
        final Map<String, String> namedResult = new HashMap<String, String>(){{
            put(STAFF_SPECIALIZATION, STAFF_SPECIALIZATION);
        }};

        final Map<String, String> whereConditions = new HashMap<String, String>(){{
            put(STAFF_USER_ID, String.valueOf(idLogin));
        }};

        try (PreparedStatement ps = db.getPrepareStatement(sql.getSelectWhereStatement(getTableName(), namedResult, whereConditions))) {
            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next() ? null : getStaffSpecialization(rs, STAFF_SPECIALIZATION);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
