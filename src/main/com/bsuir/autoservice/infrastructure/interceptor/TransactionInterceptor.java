package main.com.bsuir.autoservice.infrastructure.interceptor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.infrastructure.transaction.ITransaction;
import main.com.bsuir.autoservice.infrastructure.transaction.exception.TransactionException;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Constructor;

// transaction works only in Service Layer
@Singleton
public class TransactionInterceptor implements MethodInterceptor{
    private static final Class LAYER_GENERAL_INTERFACE = IService.class;
    private static final Class<? extends Exception> LAYER_EXCEPTION = ServiceException.class;
    private static final Constructor<? extends Exception> LAYER_EXCEPTION_CONSTRUCTOR;

    static {
        try {
            LAYER_EXCEPTION_CONSTRUCTOR = LAYER_EXCEPTION.getConstructor(Exception.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private final Provider<ITransaction> transactionProvider;

    @Inject
    public TransactionInterceptor(Provider<ITransaction> transactionProvider){
        this.transactionProvider = transactionProvider;
    }
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        checkAsserts(methodInvocation);
        
        try(ITransaction transaction = transactionProvider.get().begin()){
            Object result = methodInvocation.proceed();
            transaction.markCompleted();
            return result;
        }catch (TransactionException e){
            throw LAYER_EXCEPTION_CONSTRUCTOR.newInstance(e);
        }catch (Exception e){
            assert e.getClass() == LAYER_EXCEPTION :
                    String.format("Throwing %s type, not %s", e.getClass().getName(), LAYER_EXCEPTION.getName());
            throw e;
        }
    }

    private static void checkAsserts(MethodInvocation methodInvocation) {
        assert isHaveLayerInterface(methodInvocation.getMethod().getDeclaringClass())
                : String.format("Transaction method '%s' in class '%s' isn't implemented '%s' interface",
                methodInvocation.getMethod().getName(),
                methodInvocation.getMethod().getDeclaringClass().getName(),
                LAYER_GENERAL_INTERFACE.getName());
    }

    private static boolean isHaveLayerInterface(Class checkedClass) {
        for (Class checkedInterface : checkedClass.getInterfaces()){
            if (checkedInterface.equals(LAYER_GENERAL_INTERFACE) || isHaveLayerInterface(checkedInterface)){
                return true;
            }
        }
        return false;
    }
}
