package main.com.bsuir.autoservice.dao.crud.impl.address;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.address;

import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AddressDao extends AbstractDaoCrud<Integer, address> implements IAddressDao{

    @Inject
    public AddressDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<address> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<address> result = new LinkedList<>();
        try {
            while (rs.next()) {
                address bean = new address();

                bean.setId(rs.getInt("id"));
                bean.setId_city(rs.getInt("id_city"));
                bean.setStreet(rs.getString("street"));
                bean.setPostcode(rs.getString("postcode"));
                bean.setHouse(rs.getString("house"));
                bean.setApartment(rs.getString("apartment"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "address";
}
