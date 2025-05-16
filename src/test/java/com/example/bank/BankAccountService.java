package com.example.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {

    private BankAccountService service;

    @BeforeEach
    void setup() {
        service = new BankAccountService();
        service.createAccount("New", new BigDecimal("100.00"));
        service.createAccount("New1", new BigDecimal("50.00"));
    }

    @Test
    void deposit_ShouldUpdateBalance() {
        service.deposit("New", new BigDecimal("25.00"));
        assertEquals(new BigDecimal("125.00"),
                service.getAccount("New").getBalance());
    }

    @Test
    void withdraw_ShouldUpdateBalance() {
        service.withdraw("New", new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"),
                service.getAccount("New").getBalance());
    }

    @Test
    void transfer_ShouldMoveFunds() {
        service.transfer("New", "New1", new BigDecimal("30.00"));
        assertEquals(new BigDecimal("70.00"),
                service.getAccount("New").getBalance());
        assertEquals(new BigDecimal("80.00"),
                service.getAccount("New1").getBalance());
    }

    @Test
    void getAccount_Nonexistent_ShouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getAccount("NOPE"));
    }
}
