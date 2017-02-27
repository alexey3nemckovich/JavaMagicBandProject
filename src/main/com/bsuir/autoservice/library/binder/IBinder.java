package main.com.bsuir.autoservice.library.binder;

import javafx.util.Pair;
import main.com.bsuir.autoservice.library.binder.exception.BinderException;

import java.util.Map;

//mapping using static method valueOf
public interface IBinder {
    //map only checkedParameters with simple name
    Object mappedParameters(Class returnType, Map<String, String[]> parameters, String[] checkedParameters) throws BinderException;
    //map all returnType Field or set Default
    Object mappedParameters(Class returnType, Map<String, String[]> parameters) throws BinderException;
    //map only checkedParameters by pair : parameters value - returnType field
    Object mappedParameters(Class returnType, Map<String, String[]> parameters, Pair<String,String>[] checkedMapParameters) throws BinderException;
}
