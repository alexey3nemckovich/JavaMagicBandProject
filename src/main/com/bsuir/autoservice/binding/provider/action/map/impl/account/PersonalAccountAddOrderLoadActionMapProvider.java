package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountAddOrderLoadCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountAddOrderLoadInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountAddOrderLoadRet;

public class PersonalAccountAddOrderLoadActionMapProvider extends SingleActionMapProvider<PersonalAccountAddOrderLoadInfo, PersonalAccountAddOrderLoadRet>{

    @Inject
    protected PersonalAccountAddOrderLoadActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<PersonalAccountAddOrderLoadInfo> getCommandParamClass() {
        return PersonalAccountAddOrderLoadInfo.class;
    }

    @Override
    protected Class<? extends ICommand<PersonalAccountAddOrderLoadInfo, PersonalAccountAddOrderLoadRet>> getCommandClass() {
        return PersonalAccountAddOrderLoadCommand.class;
    }
}
