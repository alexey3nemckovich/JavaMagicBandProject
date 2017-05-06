package main.com.bsuir.autoservice.dao.crud.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.user;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends AbstractDaoCrud<user, Integer> implements IUserDao {

    @Inject
    public UserDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<user> parseResultSet(ResultSet rs) throws DaoException{
        LinkedList<user> result = new LinkedList<>();
        try {
            while (rs.next()) {
                user user = new user();
                user.setId(rs.getInt("id"));
                user.setMail(rs.getString("mail"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setType(main.com.bsuir.autoservice.bean.user.Type.valueOf(rs.getString("type")));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private static final String tableName = "user";
}
