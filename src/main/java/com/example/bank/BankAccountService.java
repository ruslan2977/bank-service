package com.example.bank;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BankAccountService {
    private final Map<String, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(String id, BigDecimal initialBalance) {
        BankAccount acc = new BankAccount(id, initialBalance);
        accounts.put(id, acc);
        return acc;
    }

    public BankAccount getAccount(String id) {
        BankAccount acc = accounts.get(id);
        if (acc == null) {
            throw new IllegalArgumentException("No such account");
        }
        return acc;
    }

    public BankAccount deposit(String id, BigDecimal amount) {
        BankAccount acc = getAccount(id);
        acc.deposit(amount);
        return acc;
    }

    public BankAccount withdraw(String id, BigDecimal amount) {
        BankAccount acc = getAccount(id);
        acc.withdraw(amount);
        return acc;
    }

    public void transfer(String fromId, String toId, BigDecimal amount) {
        BankAccount fromAcc = getAccount(fromId);
        BankAccount toAcc = getAccount(toId);
        fromAcc.withdraw(amount);
        toAcc.deposit(amount);
    }
}
