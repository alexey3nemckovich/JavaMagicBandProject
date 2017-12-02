package main.com.bsuir.autoservice.dao.crud.impl.report;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.car_status;
import main.com.bsuir.autoservice.bean.impl.city;
import main.com.bsuir.autoservice.bean.impl.report;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReportDao extends AbstractDaoCrud<Integer, report> implements IReportDao{

    @Inject
    public ReportDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<report> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<report> result = new LinkedList<>();
        try {
            while (rs.next()) {
                report bean = new report();

                bean.setId(rs.getInt("id"));
                bean.setId_payment(rs.getInt("id_payment"));
                bean.setDate(new SimpleDate(rs.getString("date")));
                bean.setContent(rs.getString("content"));

                result.add(bean);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "report";
}
