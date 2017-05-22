package main.com.bsuir.autoservice.binding.provider.action.map.impl.login;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.login.LoginCommand;
import main.com.bsuir.autoservice.command.param.LoginInfo;
import main.com.bsuir.autoservice.command.ret.LoginRet;

public class LoginActionMapProvider extends SingleActionMapProvider<LoginInfo, LoginRet> {

    @Inject
    protected LoginActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<LoginInfo> getCommandParamClass() {
        return LoginInfo.class;
    }

    @Override
    protected Class<? extends ICommand<LoginInfo, LoginRet>> getCommandClass() {
        return LoginCommand.class;
    }
}
