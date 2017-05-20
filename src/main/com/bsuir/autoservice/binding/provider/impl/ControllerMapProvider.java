package main.com.bsuir.autoservice.binding.provider.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.MapProvider;
import main.com.bsuir.autoservice.config.RouteConfig;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.NoController;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public class ControllerMapProvider extends MapProvider<String, IController> {

    @Inject
    public ControllerMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected  Map<String, IController> createBasicMap(Injector injector) {
        return new DefaultHashMap<>(injector.getInstance(NoController.class));
    }

    @Override
    protected void initMap(Injector injector) {
        try {
            addGetRequestControllers(injector);
            addPostRequestControllers(injector);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void addGetRequestControllers(Injector injector) {
        for (Map.Entry<String, Class<? extends IController>> urlControllerClass :
                injector.getInstance(RouteConfig.class).getControllerMap().entrySet()) {
            addControllerForUrlAction(urlControllerClass.getKey(),
                    injector.getInstance(urlControllerClass.getValue()));
        }
    }

    private void addPostRequestControllers(Injector injector) {
        // Not have post request
    }

    private void addControllerForUrlAction(String url,
                                           IController controller
    ) {
        map.put(url, controller);
    }
}
