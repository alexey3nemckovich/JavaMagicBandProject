package main.com.bsuir.autoservice.infrastructure.session.impl;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.config.permission.PermissionLevel;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.infrastructure.session.exception.SessionException;

import javax.servlet.http.HttpSession;

@SessionScoped
public class CustomHttpSession implements IUserSession {
    private final HttpSession httpSession;
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_LEVEL = "user_level";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_STAFF_LEVEL = "staff_level";

    @Inject
    public CustomHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public Integer getUserId() throws SessionException {
        try {
            Integer userId = (Integer) httpSession.getAttribute(KEY_USER_ID);
            assert userId != null : "Session User id is null";
            return userId;
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    private void setUserIdImpl(Integer userId) {
        httpSession.setAttribute(KEY_USER_ID, userId);
    }

    @Override
    public void setUserId(Integer userId) throws SessionException {
        try {
            setUserIdImpl(userId);
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    @Override
    public PermissionLevel getUserLevel() throws SessionException {
        try {
            PermissionLevel level = (PermissionLevel) httpSession.getAttribute(KEY_USER_LEVEL);
            return level == null
                    ? PermissionLevel.GUEST
                    : level;
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    private void setUserLevelImpl(PermissionLevel userLevel) {
        httpSession.setAttribute(KEY_USER_LEVEL, userLevel);
    }

    @Override
    public void setUserLevel(PermissionLevel userLevel) throws SessionException {
        try {
            setUserLevelImpl(userLevel);
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    @Override
    public Integer getStaffLevel() throws SessionException {
        try {
            return (Integer) httpSession.getAttribute(KEY_STAFF_LEVEL);
        }catch (Exception e){
            throw new SessionException(e);
        }
    }

    @Override
    public void setStaffLevel(Integer staffLevel) throws SessionException {
        try {
            setStaffLevelImpl(staffLevel);
        }catch (Exception e){
            throw new SessionException(e);
        }
    }

    private void setStaffLevelImpl(Integer staffLevel) {
        httpSession.setAttribute(KEY_STAFF_LEVEL, staffLevel);
    }

    @Override
    public synchronized void update(Integer userId, String userName, Staff.Specialization staffSpecialization) throws SessionException {
        try {
            setUserIdImpl(userId);
            setUserNameImpl(userName);
            setUserLevelImpl(PermissionLevel.convertStaffPermission(staffSpecialization));
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    @Override
    public boolean clear() throws SessionException {
        try {
            httpSession.invalidate();
            return true;
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    @Override
    public String getUserName() throws SessionException {
        try {
            return (String) httpSession.getAttribute(KEY_USER_NAME);
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    @Override
    public void setUserName(String userName) throws SessionException {
        try{
            setUserNameImpl(userName);
        }catch (Exception e){
            throw new SessionException(e);
        }
    }

    @Override
    public boolean isAuthorized() throws SessionException {
        return getUserName() != null;
    }

    private void setUserNameImpl(String userName) {
        httpSession.setAttribute(KEY_USER_NAME, userName);
    }
}
