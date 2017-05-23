package general.dto;

import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;
import main.com.bsuir.autoservice.dto.ShareActiveDTO;

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
}
