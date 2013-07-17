package com.qsoft.dao;

import com.qsoft.model.BankAccount;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountDAO {
    public BankAccount createAccount(String accountNumber);
    public BankAccount getAccount(String accountNumber);
    public long deposit(String accountNumber, long amount, String description);
    public long withdraw(String accountNumber, long amount, String description);
}
