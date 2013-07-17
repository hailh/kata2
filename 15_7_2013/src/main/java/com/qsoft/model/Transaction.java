package com.qsoft.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/17/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    private String accountNumber;
    private Date timestamp;
    private long amount;
    private String description;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
