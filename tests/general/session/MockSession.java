package general.session;

import main.com.bsuir.autoservice.infrastructure.session.IUserSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockSession {

    public static final int MOCK_SESSION_ID = 1;

    public static IUserSession getSession() {
        try {
            IUserSession session = mock(IUserSession.class);
            when(session.getUserId()).thenReturn(MOCK_SESSION_ID);
            return session;
        }catch (Exception e){
            throw new RuntimeException("Error when initialize session");
        }
    }
}
