package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.User;

import java.util.Objects;

public class PersonalAccountInformationRet {
    //TODO: get only important information for User
    private final boolean isContinueWork;
    private final User generalUserInformation;
    private final boolean haveNewNotification;


    public PersonalAccountInformationRet(boolean isContinueWork, User generalUserInformation,
                                          boolean haveNewNotification){
        this.isContinueWork = isContinueWork;
        this.generalUserInformation = generalUserInformation;
        this.haveNewNotification = haveNewNotification;
    }

    public static class Builder{
        private boolean nestedIsContinueWork;
        private User nestedGeneralUserInformation;
        private boolean nestedHaveNewNotification;

        public Builder setNestedIsContinueWork(boolean nestedIsContinueWork) {
            this.nestedIsContinueWork = nestedIsContinueWork;

            return this;
        }

        public Builder setNestedGeneralUserInformation(User nestedGeneralUserInformation) {
            this.nestedGeneralUserInformation = nestedGeneralUserInformation;

            return this;
        }

        public Builder setNestedHaveNewNotification(boolean nestedHaveNewNotification) {
            this.nestedHaveNewNotification = nestedHaveNewNotification;

            return this;
        }

        public PersonalAccountInformationRet build(){
            return new PersonalAccountInformationRet(
                    nestedIsContinueWork,
                    nestedGeneralUserInformation,
                    nestedHaveNewNotification);
        }
    }

    public User getGeneralUserInformation() {
        return generalUserInformation;
    }

    public boolean isContinueWork() {
        return isContinueWork;
    }

    public boolean isHaveNewNotification() {
        return haveNewNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||  getClass() != o.getClass()) return false;
        PersonalAccountInformationRet that = (PersonalAccountInformationRet) o;
        return isContinueWork == that.isContinueWork &&
                haveNewNotification == that.haveNewNotification &&
                Objects.equals(generalUserInformation, that.generalUserInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isContinueWork, generalUserInformation, haveNewNotification);
    }
}
