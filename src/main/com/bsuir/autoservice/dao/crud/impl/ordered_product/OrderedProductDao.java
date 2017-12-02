package main.com.bsuir.autoservice.dao.crud.impl.ordered_product;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.ordered_product;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderedProductDao extends AbstractDaoCrud<Integer, ordered_product> implements IOrderedProductDao{

    @Inject
    public OrderedProductDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<ordered_product> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<ordered_product> result = new LinkedList<>();
        try {
            while (rs.next()) {
                ordered_product bean = new ordered_product();

                bean.setId(rs.getInt("id"));
                bean.setId_order(rs.getInt("id_order"));
                bean.setId_product(rs.getInt("id_product"));
                bean.setId_shop_product(rs.getInt("id_shop_product"));
                bean.setCount(rs.getInt("count"));
                bean.setCount_cost(rs.getInt("count_cost"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "ordered_product";
}
