package main.com.bsuir.autoservice.dao.impl.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ServiceDao extends AbstractCrudDao<Integer, Service> implements IServiceDao {

    @Inject
    public ServiceDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Service> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Service> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Service bean = new Service();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setCost(rs.getInt("cost"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
