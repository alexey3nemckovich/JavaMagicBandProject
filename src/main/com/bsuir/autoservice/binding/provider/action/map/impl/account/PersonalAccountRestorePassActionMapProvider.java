package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountRestorePassCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountRestorePassInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountRestorePassRet;

public class PersonalAccountRestorePassActionMapProvider extends SingleActionMapProvider<PersonalAccountRestorePassInfo, PersonalAccountRestorePassRet>{

    @Inject
    protected PersonalAccountRestorePassActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<PersonalAccountRestorePassInfo> getCommandParamClass() {
        return PersonalAccountRestorePassInfo.class;
    }

    @Override
    protected Class<? extends ICommand<PersonalAccountRestorePassInfo, PersonalAccountRestorePassRet>> getCommandClass() {
        return PersonalAccountRestorePassCommand.class;
    }
}
