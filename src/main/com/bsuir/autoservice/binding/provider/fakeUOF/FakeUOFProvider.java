package main.com.bsuir.autoservice.binding.provider.fakeUOF;

import com.google.inject.Injector;
import com.google.inject.Provider;

import java.lang.reflect.Method;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class FakeUOFProvider<T> implements Provider<T> {

    private static final String GET_PREFIX = "get";
    protected final Class<T> type;
    protected final T fakeUOF;

    protected FakeUOFProvider(Injector injector, Class<T> type){
        this.type = type;
        fakeUOF = mock(type);

        try {
            for (Method getMethod : getOnlyGetMethods()) {
                // mockito was confusing by get straight injector instance
                Object loadingInstance = injector.getInstance(getMethod.getReturnType());
                when(getMethod.invoke(fakeUOF)).thenReturn(loadingInstance);
            }
        }catch (Exception e){
             throw new IllegalArgumentException(e);
        }

        initAnotherMethods(injector);
    }

    protected void initAnotherMethods(Injector injector) {
    }

    private List<Method> getOnlyGetMethods(){
        // On Arrays.asList returning a fixed-size list, can't remove elements
        List<Method> methods = new LinkedList<>(Arrays.asList(type.getMethods()));

        List<Method> ignoredMethods = getIgnoredMethods();
        methods.removeIf(method -> ignoredMethods.contains(method) || !method.getName().startsWith(GET_PREFIX));
        return methods;
    }

    private List<Method> getIgnoredMethods(){
        try {
            return new ArrayList<Method>() {{
                for (Map.Entry<String, Class[]> methodNameParameters :
                        getIgnoredMethodNamesAndParameters().entrySet()){
                    add(type.getMethod(methodNameParameters.getKey(), methodNameParameters.getValue()));
                }
            }};
        } catch (Exception e){
            throw new RuntimeException("Error in ignored method name", e);
        }
    }

    protected Map<String, Class[]> getIgnoredMethodNamesAndParameters(){
        return Collections.emptyMap();
    }

    @Override
    public T get() {
        return fakeUOF;
    }
}
