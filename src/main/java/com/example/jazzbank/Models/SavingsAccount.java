package com.example.jazzbank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SavingsAccount extends Account{
    //withdraw limit from the savings
    private final DoubleProperty withdrawLimit;

    public SavingsAccount(String owner, String accountNumber, double balance,
                           double wLimit){
        super(owner, accountNumber, balance);
        this.withdrawLimit = new SimpleDoubleProperty(this,"Withdraw Limit", wLimit);
    }
    public DoubleProperty withdrawLimitProperty(){return withdrawLimit;}

    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}
