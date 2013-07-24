package com.qsoft.dao;

import com.qsoft.model.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/17/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAO {
    void deposit(String accountNumber, long timestamp, long amount, String description);
    void withdraw(String accountNumber, long timestamp, long amount, String description);

    List<Transaction> getTransactionsOccurred(String accountNumber);
    List<Transaction> getTransactionsOccurred(String accountNumber, Date startTime, Date stopTime);
    List<Transaction> getNewTransactionsOccurred(String accountNumber, int times);
}
