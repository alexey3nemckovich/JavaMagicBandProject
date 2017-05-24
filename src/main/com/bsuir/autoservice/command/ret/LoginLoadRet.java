package main.com.bsuir.autoservice.command.ret;

public class LoginLoadRet {
    public boolean isAuthorized;
    public String userName;

    public LoginLoadRet(boolean isAuthorized, String userName) {
        this.isAuthorized = isAuthorized;
        this.userName = userName;
    }

    public static class Builder {
        private boolean authorized;
        private String userName;

        public Builder setAuthorized(boolean authorized) {
            this.authorized = authorized;

            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;

            return this;
        }


        public LoginLoadRet build() {
            return new LoginLoadRet(authorized, userName);
        }
    }
}
