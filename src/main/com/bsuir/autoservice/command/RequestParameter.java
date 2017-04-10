package main.com.bsuir.autoservice.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//annotate CommandParam field with this annotation
//if that is passed in row view throw request
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {
}
