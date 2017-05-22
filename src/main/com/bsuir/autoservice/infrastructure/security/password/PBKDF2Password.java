package main.com.bsuir.autoservice.infrastructure.security.password;

import de.rtner.security.auth.spi.SimplePBKDF2;

public class PBKDF2Password implements IPassword {

    private final SimplePBKDF2 pbkdf2;

    public PBKDF2Password(){
        pbkdf2 = new SimplePBKDF2();
    }

    @Override
    public String getHash(String hashedPassword){
        return pbkdf2.deriveKeyFormatted(hashedPassword);
    }

    @Override
    public boolean verifyPassword(String checkedPassword, String hashPassword){
        return pbkdf2.verifyKeyFormatted(hashPassword, checkedPassword);
    }
}
