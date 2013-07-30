package com.qsoft.dao.impl;

import com.qsoft.dao.AccountDAO;
import com.qsoft.model.BankAccount;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountDAOImpl implements AccountDAO{
    private Connection dbConnection;

    public AccountDAOImpl(DataSource dataSource) throws SQLException {
        this.dbConnection = dataSource.getConnection();
    }

    @Override
    public BankAccount createAccount(String accountNumber, long timestamp) throws SQLException {
        String queryString = "insert into BankAccount(accountNumber, balance, timeCreated) values ('" + accountNumber + "'," + "0," + timestamp + ")";
        dbConnection.createStatement().executeUpdate(queryString);

        queryString = "SELECT * FROM BankAccount WHERE accountNumber ='" + accountNumber + "'";
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        if(resultSet.next())
            return new BankAccount(accountNumber, resultSet.getLong("balance"),resultSet.getLong("timeCreated"));
        else
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
