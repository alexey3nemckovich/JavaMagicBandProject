package main.com.bsuir.autoservice.service.unitOfWork.exception;

public class ServiceUnitOfWorkException extends Exception {
    public ServiceUnitOfWorkException(Exception e) {
        super(e);
    }
}
