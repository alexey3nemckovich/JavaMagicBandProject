package main.com.bsuir.notepads.service.crud.exception;

public class ServiceException extends Exception {
    public ServiceException(Exception e) {
        super(e);
    }
    public ServiceException(String str){super(str);};
}
