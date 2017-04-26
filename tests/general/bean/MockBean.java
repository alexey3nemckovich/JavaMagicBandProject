package general.bean;

import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.bean.share;

import static org.mockito.Mockito.mock;

public class MockBean {
    private static final int MOCK_SERVICE_ID = 1;
    private static final String MOCK_SERVICE_NAME = "test_service_name";

    public static service getMockService(){
        service service = mock(service.class);
        service.setId(MOCK_SERVICE_ID);
        service.setName(MOCK_SERVICE_NAME);
        return service;
    }

    //TODO: fill date fields
    private static final int MOCK_SHARE_ID = 1;
    private static final String MOCK_SHARE_DESCRIPTION = "test_share_description";

    public static share getMockShare(){
        share share = mock(share.class);
        share.setId(MOCK_SHARE_ID);
        share.setDescription(MOCK_SHARE_DESCRIPTION);
        return share;
    }
}
