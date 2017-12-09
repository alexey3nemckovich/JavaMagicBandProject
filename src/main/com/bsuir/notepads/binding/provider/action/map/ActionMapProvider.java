package main.com.bsuir.notepads.binding.provider.action.map;

import com.google.inject.Injector;
import main.com.bsuir.notepads.binding.provider.MapProvider;
import main.com.bsuir.notepads.command.ICommand;
import main.com.bsuir.notepads.command.ICommandParam;
import main.com.bsuir.notepads.controller.action.Action;

public abstract class ActionMapProvider extends MapProvider<String, Action>{

    protected ActionMapProvider(Injector injector){super(injector);}

    protected void putAction(String action, Class<? extends ICommandParam> commandParamClass, ICommand command){
        map.put(action, new Action(commandParamClass, command));
    }
}
