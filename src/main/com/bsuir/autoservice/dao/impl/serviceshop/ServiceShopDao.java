package main.com.bsuir.autoservice.dao.impl.serviceshop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.ServiceShop;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceShopDao extends AbstractCrudDao<Integer, ServiceShop> implements IServiceShopDao {

    private static final String SERVICE_SHOP_ID = "id";
    private static final String SERVICE_SHOP_CITY = "city";
    private static final String SERVICE_SHOP_STREET = "street";
    private static final String SERVICE_SHOP_HOUSE = "house";
    private static final String SERVICE_SHOP_CHIEF_ID = "chief_id";

    @Inject
    public ServiceShopDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<ServiceShop> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<ServiceShop>() {{
            while (rs.next()) {
                ServiceShop bean = new ServiceShop();
                bean.setId(rs.getInt(SERVICE_SHOP_ID));
                bean.setCity(rs.getString(SERVICE_SHOP_CITY));
                bean.setStreet(rs.getString(SERVICE_SHOP_STREET));
                bean.setHouse(rs.getString(SERVICE_SHOP_HOUSE));
                bean.setChiefId(rs.getInt(SERVICE_SHOP_CHIEF_ID));
                add(bean);
            }
        }};
    }
}
