package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountViewOrdersCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountViewOrdersInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrdersRet;

public class PersonalAccountViewOrdersActionMapProvider
        extends SingleActionMapProvider<PersonalAccountViewOrdersInfo, PersonalAccountViewOrdersRet>{

    @Inject
    protected PersonalAccountViewOrdersActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<PersonalAccountViewOrdersInfo> getCommandParamClass() {
        return PersonalAccountViewOrdersInfo.class;
    }

    @Override
    protected Class<? extends ICommand<PersonalAccountViewOrdersInfo, PersonalAccountViewOrdersRet>> getCommandClass() {
        return PersonalAccountViewOrdersCommand.class;
    }
}
