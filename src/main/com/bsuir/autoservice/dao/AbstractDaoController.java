package main.com.bsuir.autoservice.dao;

import java.sql.*;
import java.util.*;

public abstract class AbstractDaoController<Entity, PrimaryKey> implements DaoController<Entity, PrimaryKey>{
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract String getSelectQuery();
    public abstract List<Entity> parseResultSet(ResultSet rs) throws DaoException;

    public List<Entity> getAll() throws DaoException{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(getSelectQuery());
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    public Entity getByPrimaryKey(PrimaryKey key) throws DaoException{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(getSelectQuery() + " WHERE" + "");
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs).get(0);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    public Entity update(Entity entity) throws DaoException{
        return  null;
    }

    public boolean delete(PrimaryKey key) throws DaoException{
        return true;
    }

    public boolean insert(Entity entity) throws DaoException{
        return  true;
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/auto_service_shop?useLegacyDatetimeCode=false&serverTimezone=UTC",
                "magicBand",
                ""
        );
    }

    private void closePreparedStatement(PreparedStatement ps) {
        if(ps != null){
            try {
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    private void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}