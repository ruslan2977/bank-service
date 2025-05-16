package com.example.bank;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void defaultConstructor_ShouldHaveZeroBalance() {
        BankAccount acct = new BankAccount();
        assertEquals(BigDecimal.ZERO, acct.getBalance());
    }

    @Test
    void deposit_PositiveAmount_ShouldIncreaseBalance() {
        BankAccount acct = new BankAccount("X1", new BigDecimal("50.00"));
        acct.deposit(new BigDecimal("25.50"));
        assertEquals(new BigDecimal("75.50"), acct.getBalance());
    }

    @Test
    void deposit_NegativeAmount_ShouldThrow() {
        BankAccount acct = new BankAccount();
        assertThrows(IllegalArgumentException.class,
                () -> acct.deposit(new BigDecimal("-10")));
    }

    @Test
    void withdraw_ValidAmount_ShouldDecreaseBalance() {
        BankAccount acct = new BankAccount("X2", new BigDecimal("100.00"));
        acct.withdraw(new BigDecimal("30.00"));
        assertEquals(new BigDecimal("70.00"), acct.getBalance());
    }

    @Test
    void withdraw_TooMuch_ShouldThrow() {
        BankAccount acct = new BankAccount("X3", new BigDecimal("20.00"));
        assertThrows(IllegalArgumentException.class,
                () -> acct.withdraw(new BigDecimal("50.00")));
    }
}
