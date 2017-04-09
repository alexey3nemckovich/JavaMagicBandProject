package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.MapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.controller.action.Action;

public abstract class ActionMapProvider extends MapProvider<String, Action>{

    protected ActionMapProvider(Injector injector){super(injector);}

    protected void putAction(String action, ICommandParam commandParam, ICommand command){
        map.put(action, new Action(commandParam, command));
    }
}
