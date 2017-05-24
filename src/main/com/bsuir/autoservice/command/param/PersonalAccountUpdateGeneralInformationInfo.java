package main.com.bsuir.autoservice.command.param;

public class PersonalAccountUpdateGeneralInformationInfo extends BaseParseCommandParam{
    private String phone;
    private String name;
    private String lastName;

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
