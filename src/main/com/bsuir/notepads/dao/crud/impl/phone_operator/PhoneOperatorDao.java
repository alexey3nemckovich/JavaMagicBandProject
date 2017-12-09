package main.com.bsuir.notepads.dao.crud.impl.phone_operator;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.phone_operator;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PhoneOperatorDao extends AbstractDaoCrud<Integer, phone_operator> implements IPhoneOperatorDao{

    @Inject
    public PhoneOperatorDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<phone_operator> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<phone_operator> result = new LinkedList<>();
        try {
            while (rs.next()) {
                phone_operator bean = new phone_operator();

                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "phone_operator";
}
