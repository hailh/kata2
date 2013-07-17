package com.qsoft.dao.impl;

import com.qsoft.dao.AccountDAO;
import com.qsoft.model.BankAccount;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountDAOImpl implements AccountDAO{

    @Override
    public BankAccount createAccount(String accountNumber) {
        return null;
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        return null;
    }

    @Override
    public long deposit(String accountNumber, long amount, String description) {
        return 0;
    }

    @Override
    public long withdraw(String accountNumber, long amount, String description) {
        return 0;
    }
}
