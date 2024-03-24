package com.example.jazzbank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account{
    //No. of transactions allowed per day for a client
    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String accountNumber, double balance,
                           int tLimit){
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this,"Transaction Limit", tLimit);
    }
    public IntegerProperty transactionLimitProperty(){return transactionLimit;}

    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}
