package com.SimpleBanking.CustomerApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import com.SimpleBanking.CustomerApp.Entity.Customer;
import com.SimpleBanking.CustomerApp.Repository.CustomerRepository;

import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        Customer existingCustomer = customerRepository.findByAccountNumber(customer.getAccountNumber());
        if(existingCustomer != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body("User saved successfully");
    }

    @PutMapping("/account/{accountNumber}")
    public ResponseEntity<Object> updateCustomerName(@PathVariable String accountNumber, @RequestBody String newName) {
        Customer existingCustomer = customerRepository.findByAccountNumber(accountNumber);
        if (existingCustomer != null) {
            existingCustomer.setName(newName);
             customerRepository.update(existingCustomer);

            return ResponseEntity.ok().body(existingCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with account number " + accountNumber + " not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            return ResponseEntity.ok("Successfully found");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user was not found");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getCustomersByName(@PathVariable String name) {
        List<Customer> customers = customerRepository.findByName(name);
        if (!customers.isEmpty()) {
            return ResponseEntity.ok("The user was found");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Object> getCustomerByAccountNumber(@PathVariable String accountNumber) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with account number " + accountNumber + " not found");
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/account/balance/{accountNumber}")
    public ResponseEntity<Object> getAccountBalance(@PathVariable String accountNumber) {
        Long bal = customerRepository.getBalance(accountNumber);
        if (bal != null) {
            return ResponseEntity.ok().body("The balance is "+ bal);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with account number " + accountNumber + " not found");
    }


    // deposit and withdrawl PUT API

    @DeleteMapping("/account/{accountNumber}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable String accountNumber) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer != null) {
            customerRepository.deleteByAccountNumber(accountNumber);
            return ResponseEntity.ok("Successfully deleted the customer");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user exists");
    }
}
