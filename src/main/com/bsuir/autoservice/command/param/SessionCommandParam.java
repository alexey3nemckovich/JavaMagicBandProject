package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.infrastructure.session.ISession;

public abstract class SessionCommandParam implements ICommandParam {
    private final ISession session;

    public SessionCommandParam(ISession session){
        this.session = session;
    }

    public ISession getSession() {
        return session;
    }
}
