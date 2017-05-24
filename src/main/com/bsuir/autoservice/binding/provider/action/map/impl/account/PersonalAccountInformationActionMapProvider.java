package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountInformationCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountInformationRet;

public class PersonalAccountInformationActionMapProvider
        extends SingleActionMapProvider<PersonalAccountInformationInfo, PersonalAccountInformationRet> {

    @Inject
    protected PersonalAccountInformationActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<PersonalAccountInformationInfo> getCommandParamClass() {
        return PersonalAccountInformationInfo.class;
    }

    @Override
    protected Class<? extends ICommand<PersonalAccountInformationInfo, PersonalAccountInformationRet>> getCommandClass() {
        return PersonalAccountInformationCommand.class;
    }
}
