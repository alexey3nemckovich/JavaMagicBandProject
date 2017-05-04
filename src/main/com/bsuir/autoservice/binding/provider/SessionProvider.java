package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.session.ISession;

@Singleton
public class SessionProvider implements Provider<ISession> {

    private final Injector injector;

    @Inject
    public SessionProvider(Injector injector){
        this.injector = injector;
    }

    @Override
    public ISession get() {
        return injector.getInstance(ISession.class);
    }
}
