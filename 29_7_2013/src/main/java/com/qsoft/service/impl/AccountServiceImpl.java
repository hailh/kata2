package com.qsoft.service.impl;

import com.qsoft.dao.AccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.BankAccount;
import com.qsoft.service.AccountService;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceImpl implements AccountService {
    AccountDAO accountDAO;
    TransactionDAO transactionDAO;
    Calendar calendar;

    @Override
    public BankAccount open(String accountNumber) {
        return accountDAO.createAccount(accountNumber);
    }

    @Override
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        return accountDAO.getAccount(accountNumber);
    }

    @Override
    public long deposit(String accountNumber, long amount, String description) {
        transactionDAO.deposit(accountNumber, calendar.getTimeInMillis(), amount, description);
        return accountDAO.deposit(accountNumber, amount, description);
    }

    @Override
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
