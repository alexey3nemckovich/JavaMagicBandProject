package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public abstract class SingleActionMapProvider<P extends ICommandParam, R> extends ActionMapProvider {

    protected SingleActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected final Map<String, Action> createBasicMap(Injector injector) {
        return new DefaultHashMap<>(createAction(getCommandParamClass(), injector.getInstance(getCommandClass())));
    }

    protected abstract Class<P> getCommandParamClass();

    protected abstract Class<? extends ICommand<P, R>> getCommandClass();

    @Override
    protected final void initMap(Injector injector) {
    }
}
