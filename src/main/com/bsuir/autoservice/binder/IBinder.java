package main.com.bsuir.autoservice.binder;

import main.com.bsuir.autoservice.binder.exception.BinderException;

import java.util.Map;

public interface IBinder {
    void addBind(String url, Class<?> aClass) throws BinderException;
    void removeBind(String url) throws BinderException;
    Object mappedParameters(String url, Map<String, String[]> parameters) throws BinderException;
}
