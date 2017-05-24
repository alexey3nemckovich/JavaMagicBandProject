package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountOrderDetailsCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountOrderDetailsInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderDetailsRet;

public class PersonalAccountOrderDetailsActionMapProvider
        extends SingleActionMapProvider<PersonalAccountOrderDetailsInfo, PersonalAccountOrderDetailsRet>{

    @Inject
    protected PersonalAccountOrderDetailsActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<PersonalAccountOrderDetailsInfo> getCommandParamClass() {
        return PersonalAccountOrderDetailsInfo.class;
    }

    @Override
    protected Class<? extends ICommand<PersonalAccountOrderDetailsInfo, PersonalAccountOrderDetailsRet>> getCommandClass() {
        return PersonalAccountOrderDetailsCommand.class;
    }
}
