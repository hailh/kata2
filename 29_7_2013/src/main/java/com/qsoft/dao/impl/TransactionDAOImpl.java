package com.qsoft.dao.impl;

import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDAOImpl implements TransactionDAO{
    @Override
    public void deposit(String accountNumber, long timestamp, long amount, String description) {

    }

    @Override
    public void withdraw(String accountNumber, long timestamp, long amount, String description) {

    }

    @Override
    public List<Transaction> getTransactionsOccurred(String accountNumber) {
        return null;
    }
}
