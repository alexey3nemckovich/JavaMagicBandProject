package main.com.bsuir.autoservice.exception;

public class ExceptionUnwrapper {

    public static Throwable getRootException(Throwable throwable) {
        if (throwable.getCause() != null)
            return getRootException(throwable.getCause());

        return throwable;
    }
}
