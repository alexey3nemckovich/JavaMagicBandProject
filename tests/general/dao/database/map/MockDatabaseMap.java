package general.dao.database.map;

import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;

import static org.mockito.Mockito.mock;

public class MockDatabaseMap {

    public static IDatabaseMap getMockDatabaseMap(){
        IDatabaseMap databaseMap = mock(IDatabaseMap.class);
        return mock(IDatabaseMap.class);
    }
}
