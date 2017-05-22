package main.com.bsuir.autoservice.dao.impl.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.User;
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

public class UserDao extends AbstractCrudDao<Integer, User> implements IUserDao {
    private static final String USER_ID = "id";
    private static final String USER_MAIL = "mail";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String USER_PHONE = "phone";
    private static final String USER_NAME = "name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_TYPE = "type";

    @Inject
    public UserDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws DaoException{
        LinkedList<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(USER_ID));
                user.setMail(rs.getString(USER_MAIL));
                user.setLogin(rs.getString(USER_LOGIN));
                user.setPassword(rs.getString(USER_PASSWORD));
                user.setPhone(rs.getString(USER_PHONE));
                user.setName(rs.getString(USER_NAME));
                user.setLastName(rs.getString(USER_LAST_NAME));
                user.setType(User.Type.valueOf(rs.getString(USER_TYPE)));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Integer checkLogin(String login, String password) throws DaoException {
        final Map<String, String> namedResult = new HashMap<String, String>(){{
            put(USER_ID, USER_ID);
        }};

        final Map<String, String> whereConditions = new HashMap<String, String>(){{
            put(USER_LOGIN, login);
            put(USER_PASSWORD, password);
        }};

        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getSelectWhereStatement(getTableName(), namedResult, whereConditions))) {
            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next() ? null : rs.getInt(USER_ID);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public String getUserName(int idLogin) throws DaoException {
        final Map<String, String> namedResult = new HashMap<String, String>(){{
            put(USER_LOGIN, USER_LOGIN);
        }};

        final Map<String, String> whereConditions = new HashMap<String, String>(){{
            put(USER_ID, String.valueOf(idLogin));
        }};

        try (PreparedStatement ps = db.getPrepareStatement(sql.getSelectWhereStatement(getTableName(), namedResult, whereConditions))) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString(USER_LOGIN);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
