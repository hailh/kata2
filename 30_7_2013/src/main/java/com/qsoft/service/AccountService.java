package com.qsoft.service;

import com.qsoft.dao.AccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.BankAccount;
import com.qsoft.model.Transaction;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountService {
    BankAccount open(String accountNumber, long timestamp) throws SQLException;

    void setAccountDAO(AccountDAO accountDAO);

    BankAccount getAccount(String accountNumber) throws SQLException;

    long deposit(String accountNumber, long amount, String description) throws SQLException;

    void setTransactionDAO(TransactionDAO transactionDAO);

    void setCalendar(Calendar calendar);

    long withdraw(String accountNumber, long amount, String description) throws SQLException;

    List<Transaction> getTransactionsOccurred(String accountNumber) throws SQLException;

    List<Transaction> getTransactionsOccurred(String accountNumber, Date startTime, Date stopTime);

    List<Transaction> getNewTransactionsOccurred(String accountNumber, int times);
}
