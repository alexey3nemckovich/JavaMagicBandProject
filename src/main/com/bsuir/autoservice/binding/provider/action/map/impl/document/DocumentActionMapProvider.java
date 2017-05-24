package main.com.bsuir.autoservice.binding.provider.action.map.impl.document;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.document.*;
import main.com.bsuir.autoservice.command.param.DocumentInfo;
import main.com.bsuir.autoservice.command.param.ShopStaffListDocumentInfo;

public class DocumentActionMapProvider extends ActionMapProvider {

    @Inject
    private DocumentActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("generateServiceListDoc", DocumentInfo.class, injector.getInstance(GenerateServiceListDocumentCommand.class));
        putAction("generateServiceShopStaffListDoc", ShopStaffListDocumentInfo.class, injector.getInstance(GenerateServiceShopStaffListDocumentCommand.class));
        putAction("generateShareListDoc", DocumentInfo.class, injector.getInstance(GenerateShareListDocumentCommand.class));
        putAction("generateSparePartListDoc", DocumentInfo.class, injector.getInstance(GenerateSparePartListDocumentCommand.class));
        putAction("generateUserListDoc", DocumentInfo.class, injector.getInstance(GenerateUserListDocumentCommand.class));
    }
}
