package main.com.bsuir.autoservice.dao.crud.impl.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.user;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends AbstractDaoCrud<Integer, user> implements IUserDao{

    @Inject
    public UserDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<user> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<user> result = new LinkedList<>();
        try {
            while (rs.next()) {
                user bean = new user();

                bean.setId(rs.getInt("id"));
                bean.setId_registration(rs.getInt("id_registration"));
                bean.setId_type(rs.getInt("id_type"));
                bean.setId_phone(rs.getInt("id_phone"));
                bean.setName(rs.getString("name"));
                bean.setLast_name(rs.getString("last_name"));
                bean.setFull_name(rs.getString("full_name"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "user";
}
