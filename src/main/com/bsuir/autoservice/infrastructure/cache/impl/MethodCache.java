package main.com.bsuir.autoservice.infrastructure.cache.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import main.com.bsuir.autoservice.infrastructure.cache.IMethodCache;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MethodCache<T, C> implements IMethodCache<T, C> {
    private final Cache<T, ConcurrentHashMap<String,Optional<C>>> cacheMethodResult;

    public MethodCache(){
        cacheMethodResult = CacheBuilder.newBuilder().weakKeys().build();
    }

    @Override
    public boolean containsMethodResult(T thisObject, String methodName){
        return containsThisObject(thisObject)
                && getThisMap(thisObject).containsKey(methodName);
    }

    private ConcurrentHashMap<String, Optional<C>> getThisMap(T thisObject){
        return cacheMethodResult.asMap().get(thisObject);
    }

    private boolean containsThisObject(T thisObject){
        return cacheMethodResult.asMap().containsKey(thisObject);
    }

    @Override
    public C getResult(T thisObject, String methodName){
        assert containsMethodResult(thisObject, methodName) : "Not exist method in map";

        Optional<C> optionalResult = getThisMap(thisObject).get(methodName);
        return optionalResult.isPresent() ? optionalResult.get() : null;
    }

    @Override
    public void setCachedResult(T thisObject, String methodName, C cachedResult){
        getInitializeThisObjectMap(thisObject).put(methodName, Optional.ofNullable(cachedResult));
    }

    private ConcurrentHashMap<String , Optional<C>> getInitializeThisObjectMap(T thisObject) {
        ConcurrentHashMap<String, Optional<C>> thisObjectMap = cacheMethodResult.getIfPresent(thisObject);
        return thisObjectMap == null ? createThisObjectMap(thisObject) : thisObjectMap;
    }

    private ConcurrentHashMap<String,Optional<C>> createThisObjectMap(T thisObject) {
        cacheMethodResult.asMap().putIfAbsent(thisObject, new ConcurrentHashMap<>());
        return getThisMap(thisObject);
    }
}
