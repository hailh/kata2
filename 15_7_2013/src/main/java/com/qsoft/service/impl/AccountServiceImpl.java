package com.qsoft.service.impl;

import com.qsoft.dao.AccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.BankAccount;
import com.qsoft.model.Transaction;
import com.qsoft.service.AccountService;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceImpl implements AccountService{
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;
    public void setUserDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public BankAccount open(String accountNumber) {
        return accountDAO.createAccount(accountNumber);
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        return accountDAO.getAccount(accountNumber);
    }

    @Override
    public long deposit(String accountNumber, long amount, String description) {
        return accountDAO.deposit(accountNumber, amount, description);
    }

    @Override
    public long withdraw(String accountNumber, long amount, String description) {
        return accountDAO.withdraw(accountNumber, amount, description);
    }

    @Override
    public List<Transaction> getTransactionsOccurred(String accountNumber) {
        return transactionDAO.getTransactionsOccurred(accountNumber);
    }

    @Override
    public List<Transaction> getTransactionsOccurred(String accountNumber, Date startTime, Date stopTime) {
        return transactionDAO.getTransactionsOccurred(accountNumber, startTime, stopTime);
    }

    @Override
    public List<Transaction> getNewTransactionsOccurred(String accountNumber, int times) {
        return transactionDAO.getNewTransactionsOccurred(accountNumber, times);
    }
}
