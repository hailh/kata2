package com.qsoft.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAO {
    void deposit(String accountNumber, long timestamp, long amount, String description);

    void withdraw(String accountNumber, long timestamp, long amount, String description);
}
