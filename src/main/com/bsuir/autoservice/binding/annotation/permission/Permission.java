package main.com.bsuir.autoservice.binding.annotation.permission;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@BindingAnnotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    PermissionLevel[] level() default PermissionLevel.USER;

    //if lower, choice all levels + levels, less than the smallest level
    //if higher, choice all levels + levels, more than the highest level
    PermissionAccessType access() default PermissionAccessType.EQUALLY_OR_HIGHER;
}
