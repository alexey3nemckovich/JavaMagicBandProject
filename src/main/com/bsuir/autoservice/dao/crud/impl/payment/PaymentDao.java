package main.com.bsuir.autoservice.dao.crud.impl.payment;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.payment;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PaymentDao extends AbstractDaoCrud<Integer, payment> implements IPaymentDao{

    @Inject
    public PaymentDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<payment> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<payment> result = new LinkedList<>();
        try {
            while (rs.next()) {
                payment bean = new payment();

                bean.setId(rs.getInt("id"));
                bean.setId_order(rs.getInt("id_order"));
                bean.setId_sender(rs.getInt("id_sender"));
                bean.setId_report(rs.getInt("id_report"));
                bean.setSum(rs.getInt("sum"));
                bean.setDate(new SimpleDate(rs.getString("date")));

                result.add(bean);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "payment";
}
