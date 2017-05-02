package main.com.bsuir.autoservice.binding.annotation.permission;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.SessionCommandParam;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PermissionInterceptor implements MethodInterceptor{
    private static final int NUMBER_ARGUMENTS_IN_COMMANDS = 1;
    private static final Constructor<CommandException> commandExceptionConstructor;


    static {
        try {
            commandExceptionConstructor = CommandException.class.getConstructor(Exception.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        checkMethodAsserts(methodInvocation);
        PermissionLevel userLevel = ((SessionCommandParam) methodInvocation.getArguments()[0])
                .getSession().getUserLevel();

        Permission permission = methodInvocation.getMethod().getAnnotation(Permission.class);
        assert permission != null : "Method isn't haved permission annotations";

        if (!isUserHavePermissionProceed(permission.level(), permission.access(), userLevel)){
            throw commandExceptionConstructor.newInstance(new IllegalAccessException("Not have permissions"));
        }

        return methodInvocation.proceed();
    }


    private static final Class CLASS_INTERFACE = ICommand.class;
    private static final Class PARAM_SUPERCLASS = SessionCommandParam.class;

    private void checkMethodAsserts(MethodInvocation methodInvocation) {
        assert Arrays.asList(methodInvocation.getMethod().getDeclaringClass().getInterfaces()).contains(CLASS_INTERFACE)
                : String.format("Calling class '%s' isn't implemented '%s'",
                        methodInvocation.getMethod().getDeclaringClass().getName(), CLASS_INTERFACE.getName());

        assert methodInvocation.getArguments().length == NUMBER_ARGUMENTS_IN_COMMANDS :
                String.format("Method '%s' in class '%s' has number arguments = '%d', but must be '%d'.",
                        methodInvocation.getMethod().getName(),
                        methodInvocation.getMethod().getDeclaringClass().getName(),
                        methodInvocation.getArguments().length,
                        NUMBER_ARGUMENTS_IN_COMMANDS);

        assert methodInvocation.getArguments()[0].getClass().getSuperclass().equals(PARAM_SUPERCLASS) :
                String.format("Method '%s' in class '%s' gets '%s' parameter, but parameter didn't extend '%s'",
                        methodInvocation.getMethod().getName(),
                        methodInvocation.getMethod().getDeclaringClass().getName(),
                        methodInvocation.getArguments()[0].getClass().getName(),
                        PARAM_SUPERCLASS.getName());
    }

    private static boolean isUserHavePermissionProceed(PermissionLevel[] accessedPermissions,
                                                       PermissionAccessType accessType,
                                                       PermissionLevel userLevel){
        List<PermissionLevel> permissions = Arrays.asList(accessedPermissions);
        if (permissions.contains(userLevel)){
            return true;
        }

        boolean havePermission;
        // equally checked early
        switch (accessType) {
            case EQUALLY_OR_LOWER:
                PermissionLevel minPermission = Collections.min(permissions);
                havePermission = minPermission.compareTo(userLevel) > 0;
                break;
            case EQUALLY_OR_HIGHER:
                PermissionLevel maxPermission = Collections.max(permissions);
                havePermission = maxPermission.compareTo(userLevel) < 0;
                break;
            case EQUALLY:
                // check early
                havePermission = false;
                break;
            default:
                throw new UnsupportedOperationException("Permission accesses aren't realized at all");
        }

        return havePermission;
    }
}
