package general.bean;

import general.session.MockSession;
import main.com.bsuir.autoservice.bean.impl.*;

import static org.mockito.Mockito.mock;

//id preferably should be unique
public class MockBean {
    public static final int MOCK_SERVICE_ID = 2;
    private static final String MOCK_SERVICE_NAME = "test_service_name";

    public static Service getMockService() {
        Service service = mock(Service.class);
        service.setId(MOCK_SERVICE_ID);
        service.setName(MOCK_SERVICE_NAME);
        return service;
    }

    //TODO: fill date fields
    public static final int MOCK_SHARE_ID = 3;
    private static final String MOCK_SHARE_DESCRIPTION = "test_share_description";

    public static Share getMockShare() {
        Share share = mock(Share.class);
        share.setId(MOCK_SHARE_ID);
        share.setDescription(MOCK_SHARE_DESCRIPTION);
        return share;
    }


    public static final int MOCK_USER_ID = MockSession.MOCK_SESSION_ID;
    public static final String MOCK_USER_NAME = "user_name";

    public static User getMockUser() {
        User user = mock(User.class);
        user.setId(MOCK_USER_ID);
        user.setName(MOCK_USER_NAME);
        return user;
    }


    public static final int MOCK_ORDER_ID = 4;

    public static Order getMockOrder() {
        Order order = mock(Order.class);
        order.setId(MOCK_ORDER_ID);
        return order;
    }

    public static final int MOCK_NOTIFICATION_ID = 5;

    public static Notification getMockNotification() {
        Notification notification = mock(Notification.class);
        notification.setId(MOCK_NOTIFICATION_ID);
        return notification;
    }

    public static final int MOCK_SERVICE_SHOP_ID = 6;

    public static ServiceShop getMockServiceShop(){
        ServiceShop serviceShop = mock(ServiceShop.class);
        serviceShop.setId(MOCK_SERVICE_SHOP_ID);
        return serviceShop;
    }

    public static final int MOCK_SPARE_PART_ID = 7;

    public static SparePart getMockSparePart() {
        SparePart sparePart = mock(SparePart.class);
        sparePart.setId(MOCK_SPARE_PART_ID);
        return sparePart;
    }
}
