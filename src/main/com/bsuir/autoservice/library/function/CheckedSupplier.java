package main.com.bsuir.autoservice.library.function;

@FunctionalInterface
public interface CheckedSupplier<T, E extends Exception> {
    T get() throws E;
}
