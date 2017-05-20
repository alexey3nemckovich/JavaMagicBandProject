package main.com.bsuir.autoservice.infrastructure.transaction.exception;

public class TransactionException extends Exception{
    public TransactionException(Exception e){
        super(e);
    }
    public TransactionException(String message, Exception e){
        super(message, e);
    }
}