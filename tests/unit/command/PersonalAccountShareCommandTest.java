package unit.command;

public class PersonalAccountShareCommandTest {
    /*
    private IShareService shareService;
    private PersonalAccountInformationCommand personalAccountShareCommand;

    @Before
    public void beforeTest(){
        shareService = getShareService();
        IServiceUnitOfWork mockUOF = getServiceUOF(shareService);
        personalAccountShareCommand = getPersonalAccountShareCommand(mockUOF);
    }

    private static IServiceUnitOfWork getServiceUOF(IShareService service){
        return new MockService.ServiceUOFBuilder()
                .setShareService(service)
                .build();
    }

    private static IShareService getShareService(){
        return MockService.getShareService();
    }

    private static final boolean MOCK_AUTHORIZED = true;
    private static final int MOCK_USER_ID = 1;

    private static PersonalAccountShareInfo getPersonalAccountShareInfo(){
        return mock(PersonalAccountShareInfo.class);
    }

    private static PersonalAccountInformationInfo getAuthorizedPersonalAccountInformationInfo(){
        PersonalAccountInformationInfo personalAccountInformationInfo = getPersonalAccountShareInfo();
        when(personalAccountInformationInfo.isAuthorized()).thenReturn(MOCK_AUTHORIZED);
        when(personalAccountInformationInfo.getUserId()).thenReturn(MOCK_USER_ID);
        return personalAccountInformationInfo;
    }

    private static PersonalAccountInformationInfo getUnauthorizedPersonalAccountInformationInfo(){
        PersonalAccountInformationInfo personalAccountInformationInfo = getPersonalAccountShareInfo();
        when(personalAccountInformationInfo.isAuthorized()).thenReturn(!MOCK_AUTHORIZED);
        return personalAccountInformationInfo;
    }

    private static PersonalAccountInformationRet getUnauthorizedPersonalAccountInformationRet(){
        return new PersonalAccountInformationRet.Builder().setNestedIsContinueWork(!MOCK_AUTHORIZED).build();
    }

    private static PersonalAccountInformationRet getAuthorizedPersonalAccountInformationRet(user user,
                                                                                            boolean haveNotification){
        return new PersonalAccountInformationRet.Builder()
                .setNestedIsContinueWork(MOCK_AUTHORIZED)
                .setNestedGeneralUserInformation(user)
                .setNestedHaveNewNotification(haveNotification)
                .build();
    }

    private static PersonalAccountInformationCommand getPersonalAccountShareCommand(
            IServiceUnitOfWork serviceUnitOfWork){
        return new PersonalAccountInformationCommand(serviceUnitOfWork);
    }

    private static user getMockUser() {
        return MockBean.getMockUser();
    }

    @Test
    public void getAccountAvailableShare() throws CommandException, ServiceException {
        user mockUser = getMockUser();
        when(shareService.getGeneralInformation(MOCK_USER_ID)).thenReturn(mockUser);
        final boolean haveNewNotification = true;
        when(notificationService.haveNewNotification()).thenReturn(haveNewNotification);
        assertEquals(personalAccountShareCommand.execute(
                getAuthorizedPersonalAccountInformationInfo()),
                getAuthorizedPersonalAccountInformationRet(mockUser, haveNewNotification));
    }

    @Test(expected = CommandException.class)
    public void getAccountAvailableShareException() throws ServiceException, CommandException {
        when(shareService.getGeneralInformation(anyInt())).thenThrow(ServiceException.class);
        when(notificationService.haveNewNotification()).thenThrow(ServiceException.class);
        personalAccountShareCommand.execute(
                getAuthorizedPersonalAccountInformationInfo()
        );
        fail();
    }
    */
}
