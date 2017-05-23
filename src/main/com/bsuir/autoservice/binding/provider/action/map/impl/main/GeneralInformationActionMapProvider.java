package main.com.bsuir.autoservice.binding.provider.action.map.impl.main;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.SingleActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.main.GeneralInformationCommand;
import main.com.bsuir.autoservice.command.param.GeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.GeneralInformationRet;

public class GeneralInformationActionMapProvider extends SingleActionMapProvider<GeneralInformationInfo, GeneralInformationRet>{

    @Inject
    protected GeneralInformationActionMapProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected Class<GeneralInformationInfo> getCommandParamClass() {
        return GeneralInformationInfo.class;
    }

    @Override
    protected Class<? extends ICommand<GeneralInformationInfo, GeneralInformationRet>> getCommandClass() {
        return GeneralInformationCommand.class;
    }
}
