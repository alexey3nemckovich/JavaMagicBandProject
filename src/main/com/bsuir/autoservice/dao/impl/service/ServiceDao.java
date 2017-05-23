package main.com.bsuir.autoservice.dao.impl.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;
import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ServiceDao extends AbstractCrudDao<Integer, Service> implements IServiceDao {

    private static final String SERVICE_ID = "id";
    private static final String SERVICE_NAME = "name";
    private static final String SERVICE_COST = "cost";

    @Inject
    public ServiceDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Service> parseResultSet(ResultSet rs) throws DaoException {
        List<Service> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Service bean = new Service();
                bean.setId(rs.getInt(SERVICE_ID));
                bean.setName(rs.getString(SERVICE_NAME));
                bean.setCost(rs.getInt(SERVICE_COST));
                result.add(bean);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ServiceAvailableDTO> getAvailable() throws DaoException {
        final Map<String, String> namedResult = new HashMap<String, String>(){{
            put(SERVICE_NAME, SERVICE_NAME);
            put(SERVICE_COST, SERVICE_COST);
        }};

        try (PreparedStatement ps = db.getPrepareStatement(sql.getSelectNamedQuery(getTableName(), namedResult))) {
            try (ResultSet rs = ps.executeQuery()) {
                return new ArrayList<ServiceAvailableDTO>(){{
                    while (rs.next()) {
                        add(new ServiceAvailableDTO(rs.getString(SERVICE_NAME), rs.getInt(SERVICE_COST)));
                    }
                }};
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
