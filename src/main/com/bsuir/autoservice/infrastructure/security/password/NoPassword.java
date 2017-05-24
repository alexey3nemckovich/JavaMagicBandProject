package main.com.bsuir.autoservice.infrastructure.security.password;

public class NoPassword implements IPassword{
    @Override
    public String getHash(String hashedPassword) {
        return hashedPassword;
    }

    @Override
    public boolean verifyPassword(String checkedPassword, String hashPassword) {
        return checkedPassword.equals(hashPassword);
    }
}
