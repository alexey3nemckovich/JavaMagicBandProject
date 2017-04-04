package main.com.bsuir.autoservice.service.exception;

public class ServiceException extends Exception {
    public ServiceException(Exception e) {
        super(e);
    }
    public ServiceException(String str){super(str);};
}
