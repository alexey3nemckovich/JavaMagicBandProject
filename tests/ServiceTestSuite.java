import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import unit.service.crud.AddBeanServiceTest;
import unit.service.crud.DeleteBeanServiceTest;
import unit.service.crud.ReadBeanServiceTest;
import unit.service.crud.UpdateBeanServiceTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    AddBeanServiceTest.class,
    DeleteBeanServiceTest.class,
    ReadBeanServiceTest.class,
    UpdateBeanServiceTest.class
})

public class ServiceTestSuite {
}
