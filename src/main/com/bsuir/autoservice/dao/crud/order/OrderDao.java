package main.com.bsuir.autoservice.dao.crud.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.util.List;

public class OrderDao extends AbstractDaoCrud<Integer, order> implements IOrderDao {

    @Inject
    public OrderDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<order> parseResultSet(ResultSet rs) throws DaoException{
        try {
            throw new NotImplementedException();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private static final String tableName = "order";
}
