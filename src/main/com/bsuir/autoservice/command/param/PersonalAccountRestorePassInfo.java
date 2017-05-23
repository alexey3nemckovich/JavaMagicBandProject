package main.com.bsuir.autoservice.command.param;


import java.util.Objects;

public class PersonalAccountRestorePassInfo extends BaseParseCommandParam{
    private String newPassword;
    private String retryPassword;


    public String getNewPassword() {
        return newPassword;
    }

    public String getRetryPassword() {
        return retryPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountRestorePassInfo that = (PersonalAccountRestorePassInfo) o;
        return Objects.equals(newPassword, that.newPassword) &&
                Objects.equals(retryPassword, that.retryPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newPassword, retryPassword);
    }
}
