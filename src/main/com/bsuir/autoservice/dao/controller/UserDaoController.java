package main.com.bsuir.autoservice.dao.controller;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.dao.AbstractDaoController;
import main.com.bsuir.autoservice.dao.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoController extends AbstractDaoController<User, Integer> {

    @Override
    public String getSelectQuery(){
        return "SELECT * FROM auto_service_shop.user";
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