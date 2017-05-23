package general.dto;

import general.bean.MockBean;
import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;
import main.com.bsuir.autoservice.dto.ShareActiveDTO;
import main.com.bsuir.autoservice.dto.UserGeneralInformationDTO;

//id preferably should be unique
public class MockDTO {
    private static final String MOCK_SERVICE_AVAILABLE_NAME = "test_available_service";
    private static final int MOCK_SERVICE_AVAILABLE_COST = 100;

    public static ServiceAvailableDTO getMockServiceAvailableDTO() {
        return new ServiceAvailableDTO(MOCK_SERVICE_AVAILABLE_NAME, MOCK_SERVICE_AVAILABLE_COST);
    }

    public static ShareActiveDTO getMockActiveSharesDTO() {
        return new ShareActiveDTO();
    }

    private static final String MOCK_USER_EMAIL = "test_user_email";
    private static final String MOCK_USER_PHONE = "+3529102514";
    private static final String MOCK_USER_NAME = MockBean.MOCK_USER_NAME;
    private static final String MOCK_USER_LAST_NAME = MockBean.MOCK_USER_LAST_NAME;

    public static UserGeneralInformationDTO getMockGeneralInformationDTO() {
        return new UserGeneralInformationDTO(MOCK_USER_EMAIL, MOCK_USER_PHONE, MOCK_USER_NAME, MOCK_USER_LAST_NAME);
    }
}
