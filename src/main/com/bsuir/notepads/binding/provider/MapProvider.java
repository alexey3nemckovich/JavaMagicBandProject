package main.com.bsuir.notepads.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import java.util.HashMap;
import java.util.Map;

public abstract class MapProvider<A, B> implements Provider<Map<A, B>> {

    protected abstract void initMap(Injector injector);

    @Inject
    protected MapProvider(Injector injector){
        map = new HashMap<A, B>();
        initMap(injector);
    }

    @Override
    public Map<A, B> get(){
        return map;
    }

    protected final Map<A, B> map;
}
