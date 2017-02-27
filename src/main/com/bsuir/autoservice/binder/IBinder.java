package main.com.bsuir.autoservice.binder;

import main.com.bsuir.autoservice.binder.exception.BinderException;

import java.util.Map;

public interface IBinder {
    Object mappedParameters(Class returnType, String[] checkedParameters, Map<String, String[]> parameters) throws BinderException;
}
