package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountMakeOrderCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountMakeOrderInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountMakeOrderRet;

public class PersonalAccountMakeOrderActionMapProvider
        extends SingleActionMapProvider<PersonalAccountMakeOrderInfo, PersonalAccountMakeOrderRet>{

    @Inject
    protected PersonalAccountMakeOrderActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<PersonalAccountMakeOrderInfo> getCommandParamClass() {
        return PersonalAccountMakeOrderInfo.class;
    }

    @Override
    protected Class<? extends ICommand<PersonalAccountMakeOrderInfo, PersonalAccountMakeOrderRet>> getCommandClass() {
        return PersonalAccountMakeOrderCommand.class;
    }
}
