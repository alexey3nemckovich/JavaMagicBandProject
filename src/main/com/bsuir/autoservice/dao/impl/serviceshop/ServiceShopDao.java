package main.com.bsuir.autoservice.dao.impl.serviceshop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.ServiceShop;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ServiceShopDao extends AbstractCrudDao<Integer, ServiceShop> implements IServiceShopDao{

    @Inject
    public ServiceShopDao(IDatabase db, ISql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<ServiceShop> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<ServiceShop> result = new LinkedList<>();
        try {
            while (rs.next()) {
                ServiceShop bean = new ServiceShop();
                bean.setId(rs.getInt("id"));
                bean.setCity(rs.getString("city"));
                bean.setStreet(rs.getString("street"));
                bean.setHouse(rs.getString("house"));
                bean.setChiefId(rs.getInt("chief_id"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
