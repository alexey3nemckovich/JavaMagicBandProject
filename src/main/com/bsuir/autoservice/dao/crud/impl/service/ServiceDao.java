package main.com.bsuir.autoservice.dao.crud.impl.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.service;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ServiceDao extends AbstractDaoCrud<Integer, service> implements IServiceDao {

    @Inject
    public ServiceDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<service> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<service> result = new LinkedList<>();
        try {
            while (rs.next()) {
                service bean = new service();
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

    private final String tableName = "service";
}
