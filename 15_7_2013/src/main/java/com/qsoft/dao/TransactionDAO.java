package com.qsoft.dao;

import com.qsoft.model.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/17/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAO {
    List<Transaction> getTransactionsOccurred(String accountNumber);
}
