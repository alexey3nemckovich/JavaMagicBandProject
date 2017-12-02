package main.com.bsuir.autoservice.dao.crud.impl.shop_product;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.shop_product;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShopProductDao extends AbstractDaoCrud<Integer, shop_product> implements IShopProductDao{

    @Inject
    public ShopProductDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<shop_product> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<shop_product> result = new LinkedList<>();
        try {
            while (rs.next()) {
                shop_product bean = new shop_product();

                bean.setId(rs.getInt("id"));
                bean.setId_shop(rs.getInt("id_shop"));
                bean.setId_product(rs.getInt("id_product"));
                bean.setAmount_available(rs.getInt("amount_available"));
                bean.setAmount_reserved(rs.getInt("amount_reserved"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "shop_product";
}
