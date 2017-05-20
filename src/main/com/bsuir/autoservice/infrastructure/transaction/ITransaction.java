package main.com.bsuir.autoservice.infrastructure.transaction;

import main.com.bsuir.autoservice.infrastructure.transaction.exception.TransactionException;

public interface ITransaction extends AutoCloseable{
    // can using as try with resources
    ITransaction begin() throws TransactionException;
    void markCompleted() throws TransactionException;
    void commit() throws TransactionException;
    void rollback() throws TransactionException;
}
