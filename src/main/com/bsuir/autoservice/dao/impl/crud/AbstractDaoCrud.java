package main.com.bsuir.autoservice.dao.impl.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.List;

//TODO: call with prepare statement set in components
public abstract class AbstractDaoCrud<Entity extends Bean, PrimaryKey> implements IDaoCrud<Entity, PrimaryKey> {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    protected abstract String getTableNameImpl();

    protected abstract List<Entity> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract String getPrimaryKeyName();

    //protected abstract String getOrderedFields();

    public String getSelectQuery(){
        return String.format("SELECT * FROM `%s`",getTableNameImpl());
    }

    @Override
    public String getTableName() throws DaoException{
        try {
            return getTableNameImpl();
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

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

    @Override
    public List<Entity> getRange(int startRange, int count) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(String.format("%s LIMIT %d, %d",
                    getSelectQuery(),startRange,count));
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    public Entity getByPrimaryKey(PrimaryKey key) throws DaoException{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(String.format("%s WHERE `%s` = '%s'",
                    getSelectQuery(),getPrimaryKeyName(), key.toString()));
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs).get(0);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }


    protected String getInsertQuery(List<Entity> insertEntities){
        throw new NotImplementedException();
        //return String.format("INSERT INTO `%s` (%s) VALUES %s",getTableNameImpl(),getOrderedFields(),getConvertedValues(insertEntities));
    }

    protected String getConvertedValues(List<Entity> insertEntities){
        StringBuilder stringBuilder = new StringBuilder();
        for (Entity insertEntity: insertEntities) {
            //TODO: complete bean get value
            throw new NotImplementedException();
            //stringBuilder.append(updateEntity.getAllFields().values());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean update(List<Entity> updateEntities) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(getUpdateQuery(updateEntities));
            ResultSet rs = ps.executeQuery();
            //TODO: think, when return false
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    protected String getUpdateQuery(List<Entity> updateEntities){
        StringBuilder stringBuilder = new StringBuilder();
        for (Entity updateEntity: updateEntities) {
            //TODO: complete bean get value
            throw new NotImplementedException();
            //stringBuilder.append(String.format("UPDATE `service` SET `name` = '1233' WHERE `service`.`id` = 1"));
        }
        return stringBuilder.toString();
    }

    protected String getDeleteQuery(List<PrimaryKey> deleteKeys) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PrimaryKey deleteKey : deleteKeys) {
            stringBuilder.append(String.format("DELETE FROM `%s` WHERE `%s`.`%s` = %s",
                    getTableNameImpl(),getTableNameImpl(), getPrimaryKeyName(), deleteKey.toString()));
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean delete(List<PrimaryKey> deleteKeys) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(getDeleteQuery(deleteKeys));
            ResultSet rs = ps.executeQuery();
            //TODO: think, when return false
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    @Override
    public boolean insert(List<Entity> insertEntities) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(getInsertQuery(insertEntities));
            ResultSet rs = ps.executeQuery();
            //TODO: think, when return false
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/auto_service_shop?useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "root"
        );
    }

    private void closePreparedStatement(PreparedStatement ps) {
        if(ps != null){
            try {
                ps.close();
            }catch (SQLException e){
                //TODO: log
                e.printStackTrace();
            }
        }
    }

    private void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                //TODO : log
                e.printStackTrace();
            }
        }
    }
}