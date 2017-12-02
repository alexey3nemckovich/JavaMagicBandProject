package main.com.bsuir.autoservice.dao.crud.impl.product_type;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.product_type;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductTypeDao extends AbstractDaoCrud<Integer, product_type> implements IProductTypeDao{

    @Inject
    public ProductTypeDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<product_type> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<product_type> result = new LinkedList<>();
        try {
            while (rs.next()) {
                product_type bean = new product_type();

                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "product_type";
}
