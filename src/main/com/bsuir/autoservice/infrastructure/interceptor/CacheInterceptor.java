package main.com.bsuir.autoservice.infrastructure.interceptor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.binding.annotation.Cached;
import main.com.bsuir.autoservice.infrastructure.cache.IMethodCache;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Singleton
public class CacheInterceptor implements MethodInterceptor{

    private final Provider<IMethodCache> methodCacheProvider;

    @Inject
    public CacheInterceptor(Provider<IMethodCache> methodCacheProvider){
        this.methodCacheProvider = methodCacheProvider;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        IMethodCache methodCache = methodCacheProvider.get();
        String methodName = getMethodName(methodInvocation);
        Object thisObject = methodInvocation.getThis();
        return methodCache.containsMethodResult(thisObject, methodName)
                ? methodCache.getResult(thisObject, methodName)
                : cachedResult(methodCache, thisObject, methodName, methodInvocation.proceed());
    }

    private static Object cachedResult(IMethodCache methodCache, Object thisObject, String methodName, Object proceed) {
        methodCache.setCachedResult(thisObject, methodName, proceed);
        return proceed;
    }

    private static String getMethodName(MethodInvocation methodInvocation){
        String annotationValue = methodInvocation.getMethod().getAnnotation(Cached.class).value();
        return annotationValue.isEmpty() ? methodInvocation.getMethod().getName() : annotationValue;
    }
}
