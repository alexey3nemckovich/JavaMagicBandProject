package main.com.bsuir.autoservice.dao.crud.impl.phone;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.phone;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PhoneDao extends AbstractDaoCrud<Integer, phone> implements IPhoneDao{

    @Inject
    public PhoneDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<phone> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<phone> result = new LinkedList<>();
        try {
            while (rs.next()) {
                phone bean = new phone();

                bean.setId(rs.getInt("id"));
                bean.setId_operator(rs.getInt("id_operator"));
                bean.setPhone_prefix(rs.getString("phone_prefix"));
                bean.setPhone_number(rs.getString("phone_number"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "phone";
}
