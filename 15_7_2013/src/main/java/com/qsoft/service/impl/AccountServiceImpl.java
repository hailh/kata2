package com.qsoft.service.impl;

import com.qsoft.dao.AccountDAO;
import com.qsoft.model.BankAccount;
import com.qsoft.service.AccountService;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceImpl implements AccountService{
    private AccountDAO accountDAO;
    public void setUserDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public BankAccount open(String accountNumber) {
        return new BankAccount(accountNumber);
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        return null;
    }

    @Override
    public long deposite(String accountNumber, long amount, String description) {
        return 0;
    }
}
