package main.com.bsuir.autoservice.binding.provider.action.map.impl.login;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.login.LogoutCommand;
import main.com.bsuir.autoservice.command.param.LogoutInfo;
import main.com.bsuir.autoservice.command.ret.LogoutRet;

public class LogoutActionMapProvider extends SingleActionMapProvider<LogoutInfo, LogoutRet>{

    @Inject
    protected LogoutActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<LogoutInfo> getCommandParamClass() {
        return LogoutInfo.class;
    }

    @Override
    protected Class<? extends ICommand<LogoutInfo, LogoutRet>> getCommandClass() {
        return LogoutCommand.class;
    }
}
