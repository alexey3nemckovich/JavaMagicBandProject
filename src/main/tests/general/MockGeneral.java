package general;

import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import static org.mockito.Mockito.mock;

public class MockGeneral {
    public static IServiceUnitOfWork getServiceUnitOfWork(){
        return mock(IServiceUnitOfWork.class);
    }
}
