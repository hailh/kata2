package com.qsoft.service;

import com.qsoft.dao.AccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.BankAccount;
import com.qsoft.model.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountService {
    void setUserDAO(AccountDAO accountDAO);

    void setTransactionDAO(TransactionDAO transactionDAO);

    BankAccount open(String accountNumber);

    BankAccount getAccount(String accountNumber);

    long deposit(String accountNumber, long amount, String description);

    long withdraw(String accountNumber, long amount, String description);

    List<Transaction> getTransactionsOccurred(String accountNumber);

    List<Transaction> getTransactionsOccurred(String accountNumber, Date startTime, Date stopTime);

    List<Transaction> getNewTransactionsOccurred(String accountNumber, int times);
}
