package main.com.bsuir.autoservice.dao.impl.ordersparepart;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.OrderSparePart;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderSparePartDao extends AbstractCrudDao<NullType, OrderSparePart> implements IOrderSparePartDao {

    private static final String SPARE_PART_ID = "spare_part_id";
    private static final String ORDER_ID = "order_id";
    private static final String SPARE_PART_ORDER_COUNT = "count";

    @Inject
    public OrderSparePartDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<OrderSparePart> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<OrderSparePart>() {{
            while (rs.next()) {
                OrderSparePart bean = new OrderSparePart();
                bean.setSparePartId(rs.getInt(SPARE_PART_ID));
                bean.setOrderId(rs.getInt(ORDER_ID));
                bean.setCount(rs.getInt(SPARE_PART_ORDER_COUNT));
                add(bean);
            }
        }};
    }
}
