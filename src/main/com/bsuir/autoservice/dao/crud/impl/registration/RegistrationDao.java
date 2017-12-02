package main.com.bsuir.autoservice.dao.crud.impl.registration;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.registration;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RegistrationDao extends AbstractDaoCrud<Integer, registration> implements IRegistrationDao{

    @Inject
    public RegistrationDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<registration> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<registration> result = new LinkedList<>();
        try {
            while (rs.next()) {
                registration bean = new registration();

                bean.setId(rs.getInt("id"));
                bean.setEmail_user(rs.getString("email_user"));
                bean.setEmail_domen(rs.getString("email_domen"));
                bean.setFull_email(rs.getString("full_email"));
                bean.setLogin(rs.getString("login"));
                bean.setPassword(rs.getString("password"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "registration";
}
