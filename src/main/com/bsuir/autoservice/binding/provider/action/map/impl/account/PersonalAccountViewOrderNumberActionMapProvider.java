package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountViewOrderNumberCommand;
import main.com.bsuir.autoservice.command.param.NoInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrderNumberRet;

public class PersonalAccountViewOrderNumberActionMapProvider extends SingleActionMapProvider<NoInfo, PersonalAccountViewOrderNumberRet>{

    @Inject
    protected PersonalAccountViewOrderNumberActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<NoInfo> getCommandParamClass() {
        return NoInfo.class;
    }

    @Override
    protected Class<? extends ICommand<NoInfo, PersonalAccountViewOrderNumberRet>> getCommandClass() {
        return PersonalAccountViewOrderNumberCommand.class;
    }
}
