package general.bean;

import general.session.MockSession;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.bean.share;

import static org.mockito.Mockito.mock;

public class MockBean {
    public static final int MOCK_SERVICE_ID = 4;
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


    public static final int MOCK_ORDER_ID = 2;

    public static order getMockOrder() {
        order order = mock(order.class);
        order.setId(MOCK_ORDER_ID);
        return order;
    }
}
