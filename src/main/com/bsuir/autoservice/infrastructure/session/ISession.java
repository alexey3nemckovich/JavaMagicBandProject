package main.com.bsuir.autoservice.infrastructure.session;

import main.com.bsuir.autoservice.config.permission.PermissionLevel;
import main.com.bsuir.autoservice.infrastructure.session.exception.SessionException;

public interface ISession<UserIdType, StaffLevel ,StaffServiceShopId>{
    UserIdType getUserId() throws SessionException;
    void setUserId(UserIdType userId) throws SessionException;
    PermissionLevel getUserLevel() throws SessionException;
    void setUserLevel(PermissionLevel userLevel) throws SessionException;
    StaffLevel getStaffLevel() throws SessionException;
    void setStaffLevel(StaffLevel staffLevel) throws SessionException;
    void update(UserIdType userId, String userName, PermissionLevel userLevel) throws SessionException;
    StaffServiceShopId getStaffServiceShopId() throws SessionException;
    void setStaffServiceShopId(StaffServiceShopId staffServiceShopId) throws SessionException;
    String getUserName() throws SessionException;
    void setUserName(String userName) throws SessionException;
}
