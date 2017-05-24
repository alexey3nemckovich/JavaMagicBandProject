package main.com.bsuir.autoservice.dto;

import java.util.Objects;

public class UserGeneralInformationDTO {
    private final String userMail;
    private final String userPhone;
    private final String userName;
    private final String userLastName;

    public UserGeneralInformationDTO(String userMail, String userPhone, String userName, String userLastName) {
        this.userMail = userMail;
        this.userPhone = userPhone;
        this.userName = userName;
        this.userLastName = userLastName;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGeneralInformationDTO that = (UserGeneralInformationDTO) o;
        return Objects.equals(userMail, that.userMail) &&
                Objects.equals(userPhone, that.userPhone) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userLastName, that.userLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMail, userPhone, userName, userLastName);
    }
}
