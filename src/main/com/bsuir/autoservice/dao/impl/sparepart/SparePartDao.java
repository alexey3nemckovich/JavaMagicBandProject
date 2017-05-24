package main.com.bsuir.autoservice.dao.impl.sparepart;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.SparePart;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SparePartDao extends AbstractCrudDao<Integer, SparePart> implements ISparePartDao {

    private static final String SPARE_PART_ID = "id";
    private static final String SPARE_PART_NAME = "name";
    private static final String SPARE_PART_AMOUNT = "amount_available";

    @Inject
    public SparePartDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<SparePart> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<SparePart>() {{
            while (rs.next()) {
                SparePart bean = new SparePart();
                bean.setId(rs.getInt(SPARE_PART_ID));
                bean.setName(rs.getString(SPARE_PART_NAME));
                bean.setAmountAvailable(rs.getInt(SPARE_PART_AMOUNT));
                add(bean);
            }
        }};
    }
}
