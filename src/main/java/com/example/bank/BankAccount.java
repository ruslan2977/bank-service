package com.example.bank;

import java.math.BigDecimal;

public class BankAccount {
    private String id;
    private BigDecimal balance;


    public BankAccount() {
        this.id = "";
        this.balance = BigDecimal.ZERO;
    }


    public BankAccount(String id, BigDecimal initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit should be > 0");
        }
        balance = balance.add(amount);
    }


    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount should be > 0");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Not enough money");
        }
        balance = balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Account " + id + " Balance: " + balance;
    }
}