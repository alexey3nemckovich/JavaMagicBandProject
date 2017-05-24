package main.com.bsuir.autoservice.binding.provider.action.map.impl.account;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.account.PersonalAccountUpdateGeneralInformationCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountUpdateGeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountUpdateGeneralInformationRet;

public class PersonalAccountUpdateGeneralInformationProvider extends
        SingleActionMapProvider<PersonalAccountUpdateGeneralInformationInfo, PersonalAccountUpdateGeneralInformationRet> {

    @Inject
    protected PersonalAccountUpdateGeneralInformationProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<PersonalAccountUpdateGeneralInformationInfo> getCommandParamClass() {
        return PersonalAccountUpdateGeneralInformationInfo.class;
    }

    @Override
    protected Class<? extends ICommand<PersonalAccountUpdateGeneralInformationInfo, PersonalAccountUpdateGeneralInformationRet>> getCommandClass() {
        return PersonalAccountUpdateGeneralInformationCommand.class;
    }
}
