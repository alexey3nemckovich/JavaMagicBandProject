package main.com.bsuir.autoservice.dao.crud.impl.shop;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.shop;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShopDao extends AbstractDaoCrud<Integer, shop> implements IShopDao{

    @Inject
    public ShopDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<shop> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<shop> result = new LinkedList<>();
        try {
            while (rs.next()) {
                shop bean = new shop();

                bean.setId(rs.getInt("id"));
                bean.setId_address(rs.getInt("id_address"));
                bean.setId_manager(rs.getInt("id_manager"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "shop";
}
