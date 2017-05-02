package main.com.bsuir.autoservice.session.impl;

import main.com.bsuir.autoservice.binding.annotation.permission.PermissionLevel;
import main.com.bsuir.autoservice.session.ISession;
import main.com.bsuir.autoservice.session.exception.SessionException;

import javax.servlet.http.HttpSession;

public class CustomHttpSession implements ISession<Integer> {
    private final HttpSession httpSession;
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_LEVEL = "user_level";

    public CustomHttpSession(HttpSession httpSession){
        this.httpSession = httpSession;
    }

    @Override
    public Integer getUserId() throws SessionException {
        try {
            Integer userId = (Integer) httpSession.getAttribute(KEY_USER_ID);
            assert userId != null : "Session user id is null";
            return userId;
        }catch (Exception e){
            throw new SessionException(e);
        }
    }

    private void setUserIdImpl(Integer userId){
        httpSession.setAttribute(KEY_USER_ID, userId);
    }

    @Override
    public void setUserId(Integer userId) throws SessionException {
        try {
            setUserIdImpl(userId);
        }catch (Exception e){
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
        }catch (Exception e){
            throw new SessionException(e);
        }
    }

    private void setUserLevelImpl(PermissionLevel userLevel){
        httpSession.setAttribute(KEY_USER_LEVEL, userLevel);
    }

    @Override
    public void setUserLevel(PermissionLevel userLevel) throws SessionException {
        try {
            setUserLevelImpl(userLevel);
        }catch (Exception e){
            throw new SessionException(e);
        }
    }

    @Override
    public void update(Integer userId, PermissionLevel userLevel) throws SessionException {
        try{
            setUserIdImpl(userId);
            setUserLevelImpl(userLevel);
        }catch (Exception e){
            throw new SessionException(e);
        }
    }
}
