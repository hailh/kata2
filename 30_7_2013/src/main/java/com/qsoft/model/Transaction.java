package com.qsoft.model;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/30/13
 * Time: 8:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    private String accountNumber;
    private long timestamp;
    private long amount;
    private String description;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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
