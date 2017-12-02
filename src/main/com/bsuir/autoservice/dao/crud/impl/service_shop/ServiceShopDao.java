package main.com.bsuir.autoservice.dao.crud.impl.service_shop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.service_shop;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ServiceShopDao extends AbstractDaoCrud<Integer, service_shop> implements IServiceShopDao{

    @Inject
    public ServiceShopDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<service_shop> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<service_shop> result = new LinkedList<>();
        try {
            while (rs.next()) {
                service_shop bean = new service_shop();
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

    private final String tableName = "service_shop";
}
