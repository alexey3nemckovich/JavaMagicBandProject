package general.bean;

import general.session.MockSession;
import main.com.bsuir.autoservice.bean.*;

import static org.mockito.Mockito.mock;

//id preferably should be unique
public class MockBean {
    public static final int MOCK_SERVICE_ID = 2;
    private static final String MOCK_SERVICE_NAME = "test_service_name";

    public static service getMockService() {
        service service = mock(service.class);
        service.setId(MOCK_SERVICE_ID);
        service.setName(MOCK_SERVICE_NAME);
        return service;
    }

    //TODO: fill date fields
    public static final int MOCK_SHARE_ID = 3;
    private static final String MOCK_SHARE_DESCRIPTION = "test_share_description";

    public static share getMockShare() {
        share share = mock(share.class);
        share.setId(MOCK_SHARE_ID);
        share.setDescription(MOCK_SHARE_DESCRIPTION);
        return share;
    }


    public static final int MOCK_USER_ID = MockSession.MOCK_SESSION_ID;

    public static User getMockUser() {
        User user = mock(User.class);
        user.setId(MOCK_USER_ID);
        return user;
    }


    public static final int MOCK_ORDER_ID = 4;

    public static order getMockOrder() {
        order order = mock(order.class);
        order.setId(MOCK_ORDER_ID);
        return order;
    }

    public static final int MOCK_NOTIFICATION_ID = 5;

    public static notification getMockNotification() {
        notification notification = mock(notification.class);
        notification.setId(MOCK_NOTIFICATION_ID);
        return notification;
    }

    public static final int MOCK_SERVICE_SHOP_ID = 6;

    public static service_shop getMockServiceShop(){
        service_shop serviceShop = mock(service_shop.class);
        serviceShop.setId(MOCK_SERVICE_SHOP_ID);
        return serviceShop;
    }

    public static final int MOCK_SPARE_PART_ID = 7;

    public static spare_part getMockSparePart() {
        spare_part sparePart = mock(spare_part.class);
        sparePart.setSparePartId(MOCK_SPARE_PART_ID);
        return sparePart;
    }
}
