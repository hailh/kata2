package com.qsoft.service;

import com.qsoft.model.BankAccount;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountService {
    BankAccount open(String accountNumber);

    BankAccount getAccount(String accountNumber);

    long deposite(String accountNumber, long amount, String description);
}
