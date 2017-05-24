package main.com.bsuir.autoservice.binding.provider.action.map.impl.login;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.login.LoginLoadCommand;
import main.com.bsuir.autoservice.command.param.LoginLoadInfo;
import main.com.bsuir.autoservice.command.ret.LoginLoadRet;

public class LoginLoadActionMapProvider extends SingleActionMapProvider<LoginLoadInfo, LoginLoadRet> {
    @Inject
    private LoginLoadActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected  Class<LoginLoadInfo> getCommandParamClass() {
        return LoginLoadInfo.class;
    }

    @Override
    protected  Class<? extends ICommand<LoginLoadInfo, LoginLoadRet>> getCommandClass() {
        return LoginLoadCommand.class;
    }
}
