import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import unit.command.main.GeneralInformationCommandTest;
import unit.command.main.LoginCommandTest;
import unit.command.main.ResetLoginCommandTest;
import unit.command.mechanic.*;
import unit.command.personalaccount.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    GeneralInformationCommandTest.class,
    LoginCommandTest.class,
    ResetLoginCommandTest.class,

    ChiefMechanicChangeSparePartCommandTest.class,
    ChiefMechanicViewSparePartCommandTest.class,
    MechanicAddOrderNotificationCommandTest.class,
    MechanicChangeOrderStateCommandTest.class,
    MechanicViewOrdersCommandTest.class,

    PersonalAccountAvailableOrderServicesCommandTest.class,
    PersonalAccountInformationCommandTest.class,
    PersonalAccountMakeOrderTest.class,
    PersonalAccountOrderDetailsCommandTest.class,
    PersonalAccountOrderNotificationsCommandTest.class,
    PersonalAccountShareCommandTest.class,
    PersonalAccountUpdateGeneralInformationCommandTest.class,
    PersonalAccountViewOrdersCommandTest.class
})

public class CommandTestSuite {
}
