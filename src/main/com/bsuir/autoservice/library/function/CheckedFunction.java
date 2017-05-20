package main.com.bsuir.autoservice.library.function;

@FunctionalInterface
public interface CheckedFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}
