package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.MapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.controller.action.Action;

public abstract class ActionMapProvider extends MapProvider<String, Action>{

    protected ActionMapProvider(Injector injector) {
        super(injector);
    }

    protected final <P extends ICommandParam ,R> void putAction(String action, Class<P> commandParamClass, ICommand<P, R> command){
        map.put(action, createAction(commandParamClass, command));
    }

    protected final <P extends ICommandParam ,R> Action<P, R> createAction(Class<P> commandParamClass, ICommand<P, R> command){
        return new Action<>(commandParamClass, command);
    }
}
