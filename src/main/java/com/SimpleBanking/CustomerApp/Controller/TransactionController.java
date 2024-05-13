package com.SimpleBanking.CustomerApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.SimpleBanking.CustomerApp.Entity.Customer;
import com.SimpleBanking.CustomerApp.Repository.CustomerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/customers")
public class TransactionController {
    @Autowired
    private CustomerRepository customerRepository;

    @PutMapping("/account/deposit/{accountNumber}")
    public ResponseEntity<Object> depositAmount(@PathVariable String accountNumber, @RequestBody Long amount) {
        Long bal = customerRepository.getBalance(accountNumber);
        if (bal != null) {
            Customer customer = customerRepository.findByAccountNumber(accountNumber);
            Long sum = customer.getBalance() + amount;
            customer.setBalance(sum);
            Long newBal = customerRepository.updateBalance(customer, sum);
            return ResponseEntity.ok().body("Successfully deposited money, now the balance is " + newBal);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }

    @PutMapping("/account/withdraw/{accountNumber}")
    public ResponseEntity<Object> withdraw(@PathVariable String accountNumber, @RequestBody Long amount) {
        Long bal = customerRepository.getBalance(accountNumber);
        if (bal != null) {
            if (bal >= amount) {
                Customer customer = customerRepository.findByAccountNumber(accountNumber);
                Long newBalance = customer.getBalance() - amount;
                customer.setBalance(newBalance);
                Long updatedBalance = customerRepository.updateBalance(customer, newBalance);
                return ResponseEntity.ok("Successfully withdrawn money, now the balance is " + updatedBalance);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Balance");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}

