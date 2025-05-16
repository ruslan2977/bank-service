package com.example.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    @PostMapping
    public ResponseEntity<BankAccount> create(@RequestBody Map<String, String> body) {
        String id = body.get("id");
        BigDecimal initial = new BigDecimal(body.getOrDefault("initialBalance", "0"));
        BankAccount acc = service.createAccount(id, initial);
        return ResponseEntity.ok(acc);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<String> printBalance(@PathVariable String id) {
        BankAccount acc = service.getAccount(id);
        return ResponseEntity.ok(acc.toString());
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<BankAccount> deposit(
            @PathVariable String id,
            @RequestBody Map<String, String> body
    ) {
        BigDecimal amt = new BigDecimal(body.get("amount"));
        BankAccount acc = service.deposit(id, amt);
        return ResponseEntity.ok(acc);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<BankAccount> withdraw(
            @PathVariable String id,
            @RequestBody Map<String, String> body
    ) {
        BigDecimal amt = new BigDecimal(body.get("amount"));
        BankAccount acc = service.withdraw(id, amt);
        return ResponseEntity.ok(acc);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody Map<String, String> body) {
        String from = body.get("fromId");
        String to = body.get("toId");
        BigDecimal amt = new BigDecimal(body.get("amount"));
        service.transfer(from, to, amt);
        return ResponseEntity.ok("Transferred " + amt + " from " + from + " to " + to);
    }
}