package main.com.bsuir.notepads.dao.crud.impl.user_type;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.user_type;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserTypeDao extends AbstractDaoCrud<Integer, user_type> implements IUserTypeDao{

    @Inject
    public UserTypeDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<user_type> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<user_type> result = new LinkedList<>();
        try {
            while (rs.next()) {
                user_type bean = new user_type();

                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "user_type";
}
