package main.com.bsuir.autoservice.infrastructure.security.password;

public interface IPassword {
    String getHash(String hashedPassword);
    boolean verifyPassword(String checkedPassword, String hashPassword);
}
