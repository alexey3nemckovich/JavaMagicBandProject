package main.com.bsuir.autoservice.infrastructure.session.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.config.permission.PermissionLevel;
import main.com.bsuir.autoservice.infrastructure.session.ISession;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.infrastructure.session.exception.SessionException;

@Singleton
public class CustomHttpSessionProvider implements IUserSession {
    private final Provider<CustomHttpSession> customHttpSessionProvider;

    @Inject
    public CustomHttpSessionProvider(Provider<CustomHttpSession> customHttpSessionProvider){
        this.customHttpSessionProvider = customHttpSessionProvider;
    }

    private ISession<Integer> getSession(){
        return customHttpSessionProvider.get();
    }

    @Override
    public Integer getUserId() throws SessionException {
        return getSession().getUserId();
    }

    @Override
    public void setUserId(Integer userId) throws SessionException {
        getSession().setUserId(userId);
    }

    @Override
    public PermissionLevel getUserLevel() throws SessionException {
        return getSession().getUserLevel();
    }

    @Override
    public void setUserLevel(PermissionLevel userLevel) throws SessionException {
        getSession().setUserLevel(userLevel);
    }

    @Override
    public void update(Integer userId, PermissionLevel userLevel) throws SessionException {
        getSession().update(userId, userLevel);
    }
}
