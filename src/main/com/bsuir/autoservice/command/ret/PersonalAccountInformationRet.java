package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.dto.UserGeneralInformationDTO;

import java.util.Objects;

public class PersonalAccountInformationRet {
    private final UserGeneralInformationDTO generalUserInformation;
    private final boolean haveNewNotification;


    public PersonalAccountInformationRet(UserGeneralInformationDTO generalUserInformation,
                                         boolean haveNewNotification){
        this.generalUserInformation = generalUserInformation;
        this.haveNewNotification = haveNewNotification;
    }

    public UserGeneralInformationDTO getGeneralUserInformation() {
        return generalUserInformation;
    }

    public boolean isHaveNewNotification() {
        return haveNewNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||  getClass() != o.getClass()) return false;
        PersonalAccountInformationRet that = (PersonalAccountInformationRet) o;
        return haveNewNotification == that.haveNewNotification &&
                Objects.equals(generalUserInformation, that.generalUserInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generalUserInformation, haveNewNotification);
    }
}
