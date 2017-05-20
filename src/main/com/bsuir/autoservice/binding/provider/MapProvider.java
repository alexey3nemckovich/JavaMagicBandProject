package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import java.util.HashMap;
import java.util.Map;

public abstract class MapProvider<A, B> implements Provider<Map<A, B>> {

    protected abstract void initMap(Injector injector);

    protected Map<A,B> createBasicMap(Injector injector){
        return new HashMap<>();
    }

    @Inject
    protected MapProvider(Injector injector){
        map = createBasicMap(injector);
        initMap(injector);
    }

    @Override
    public final Map<A, B> get(){
        return map;
    }

    protected Map<A, B> map;
}
