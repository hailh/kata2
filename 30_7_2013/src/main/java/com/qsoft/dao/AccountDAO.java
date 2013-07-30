package com.qsoft.dao;

import com.qsoft.model.BankAccount;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountDAO {
    BankAccount createAccount(String accountNumber, long timestamp);

    BankAccount getAccount(String accountNumber);

    long deposit(String accountNumber, long amount, String description);

    long withdraw(String accountNumber, long amount, String description);
}
