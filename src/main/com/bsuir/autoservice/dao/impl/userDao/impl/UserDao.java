package main.com.bsuir.autoservice.dao.impl.userDao.impl;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.dao.AbstractDaoController;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.userDao.IUserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends AbstractDaoController<User, Integer> implements IUserDao {
    private static final String tableName = "user";
    private static final String primaryKeyName = "id";

    @Override
    public String getTableNameImpl() {
        return tableName;
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws DaoException{
        LinkedList<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setMail(rs.getString("mail"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setType(User.Type.valueOf(rs.getString("type")));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}