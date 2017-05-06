package main.com.bsuir.autoservice.infrastructure.transaction.impl;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.infrastructure.transaction.ITransaction;
import main.com.bsuir.autoservice.infrastructure.transaction.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

@RequestScoped
public class RequestTransaction implements ITransaction {
    private enum TransactionState{
        PREPARE ,IN_TRANSACTION, MARKED, COMPLETED
    }

    private final IDatabase requestDatabase;
    private Connection connection;
    private boolean lastAutoCommitState;
    private TransactionState transactionState;

    @Inject
    public RequestTransaction(IDatabase requestDatabase){
        this.requestDatabase = requestDatabase;
        connection = null;
        transactionState = TransactionState.PREPARE;
    }

    @Override
    public ITransaction begin() throws TransactionException {
        assert transactionState != TransactionState.IN_TRANSACTION : "Transaction isn't in transaction state";
        try {
            connection = transactionState == TransactionState.PREPARE
                    ? requestDatabase.getConnection()
                    : connection;
            lastAutoCommitState = connection.getAutoCommit();
            connection.setAutoCommit(false);
            transactionState = TransactionState.IN_TRANSACTION;

            return this;
        }catch (Exception e){
            throw new TransactionException(e);
        }
    }

    @Override
    public void commit() throws TransactionException {
        assert transactionState == TransactionState.IN_TRANSACTION || transactionState == TransactionState.MARKED
                : "Transaction isn't in transaction state";

        try {
            connection.commit();
            completeTransaction();
        } catch (Exception e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        assertInTransactionState();
        try {
            connection.rollback();
            completeTransaction();
        } catch (Exception e) {
            throw new TransactionException(e);
        }
    }

    private void completeTransaction() throws SQLException {
        connection.setAutoCommit(lastAutoCommitState);
        transactionState = TransactionState.COMPLETED;
    }

    private void assertInTransactionState(){
        assert transactionState == TransactionState.IN_TRANSACTION : "Transaction isn't in transaction state";
    }

    @Override
    public void markCompleted() throws TransactionException {
        assertInTransactionState();
        transactionState = TransactionState.MARKED;
    }

    @Override
    public void close() throws TransactionException {
        assert transactionState != TransactionState.PREPARE : "Transaction is in prepare state, not in transaction";
        assert transactionState != TransactionState.COMPLETED : "Transaction is completed yet";
        switch (transactionState){
            case MARKED:
                commit();
                break;
            case IN_TRANSACTION:
                rollback();
                break;
            default:
                throw new UnsupportedOperationException(String.format("Not implemented %s transactionState",
                        transactionState.toString()));
        }
    }
}
