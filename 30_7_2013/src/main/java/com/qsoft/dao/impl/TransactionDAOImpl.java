package com.qsoft.dao.impl;

import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDAOImpl implements TransactionDAO{
    private Connection dbConnection;

    public TransactionDAOImpl(DataSource dataSource) throws SQLException {
        this.dbConnection = dataSource.getConnection();
    }

    @Override
    public void deposit(String accountNumber, long timestamp, long amount, String description) throws SQLException {
        String queryString = "insert into Transactions(accountNumber, amount, timeCreated, description) values ('" + accountNumber + "'," + amount + "," + timestamp + ",'" + description + "')";
        dbConnection.createStatement().executeUpdate(queryString);
    }

    @Override
    public void withdraw(String accountNumber, long timestamp, long amount, String description) throws SQLException {
        String queryString = "insert into Transactions(accountNumber, amount, timeCreated, description) values ('" + accountNumber + "'," + (-1)*amount + "," + timestamp + ",'" + description + "')";
        dbConnection.createStatement().executeUpdate(queryString);
    }

    @Override
    public List<Transaction> getTransactionsOccurred(String accountNumber) throws SQLException {
        String queryString = "SELECT * FROM Transactions WHERE accountNumber ='" + accountNumber + "'";
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        List<Transaction> results = new ArrayList<Transaction>();
        while (resultSet.next()){
            results.add(new Transaction(accountNumber, resultSet.getLong("timeCreated"), resultSet.getLong("amount"), resultSet.getString("description")));
        }
        return results;
    }

    @Override
    public List<Transaction> getTransactionsOccurred(String accountNumber, long startTime, long stopTime) throws SQLException {
        String queryString = "SELECT * FROM Transactions WHERE accountNumber ='" + accountNumber + "' and timeCreated >= " + startTime + " and timeCreated <= " + stopTime;
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        List<Transaction> results = new ArrayList<Transaction>();
        while (resultSet.next()){
            results.add(new Transaction(accountNumber, resultSet.getLong("timeCreated"), resultSet.getLong("amount"), resultSet.getString("description")));
        }
        return results;
    }

    @Override
    public List<Transaction> getNewTransactionsOccurred(String accountNumber, int times) throws SQLException {
        String queryString = "SELECT top "+ times +" * FROM Transactions WHERE accountNumber ='" + accountNumber + "'";
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        List<Transaction> results = new ArrayList<Transaction>();
        while (resultSet.next()){
            results.add(new Transaction(accountNumber, resultSet.getLong("timeCreated"), resultSet.getLong("amount"), resultSet.getString("description")));
        }
        return results;
    }
}
