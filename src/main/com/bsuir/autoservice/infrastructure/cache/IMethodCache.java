package main.com.bsuir.autoservice.infrastructure.cache;

public interface IMethodCache<T, C> {
    boolean containsMethodResult(T thisObject, String methodName);
    C getResult(T thisObject, String methodName);
    void setCachedResult(T thisObject, String methodName, C cachedResult);
}
