package main.com.bsuir.autoservice.session;

import main.com.bsuir.autoservice.config.permission.PermissionLevel;
import main.com.bsuir.autoservice.session.exception.SessionException;

public interface ISession<UserIdType> {
    UserIdType getUserId() throws SessionException;
    void setUserId(UserIdType userId) throws SessionException;
    PermissionLevel getUserLevel() throws SessionException;
    void setUserLevel(PermissionLevel userLevel) throws SessionException;
    void update(UserIdType userId, PermissionLevel userLevel) throws SessionException;
}
