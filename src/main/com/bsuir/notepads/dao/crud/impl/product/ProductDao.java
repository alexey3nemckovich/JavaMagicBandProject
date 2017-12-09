package main.com.bsuir.notepads.dao.crud.impl.product;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.product;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDao extends AbstractDaoCrud<Integer, product> implements IProductDao{

    @Inject
    public ProductDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<product> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<product> result = new LinkedList<>();
        try {
            while (rs.next()) {
                product bean = new product();

                bean.setId(rs.getInt("id"));
                bean.setId_product_type(rs.getInt("id_product_type"));
                bean.setName(rs.getString("name"));
                bean.setInfo(rs.getString("info"));
                bean.setCost(rs.getInt("cost"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "product";
}
