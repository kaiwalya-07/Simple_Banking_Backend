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
public class NomineeController {

    @Autowired
    private CustomerRepository customerRepository;
    @PutMapping("/account/nominee/{accountNumber}")
    public ResponseEntity<Object> updateCustomerName(@PathVariable String accountNumber, @RequestBody String newNominee) {
        Customer existingCustomer = customerRepository.findByAccountNumber(accountNumber);
        Customer existingNominee = customerRepository.findByAccountNumber(newNominee);
        if (existingCustomer != null && existingNominee!=null) {
            boolean NomineeAdd=customerRepository.addNominee(existingCustomer,newNominee);
            if(NomineeAdd){
                return ResponseEntity.ok().body("Succesfully added the nominee,please check the database");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot add");

        } else if(existingCustomer==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with account number " + accountNumber + " not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nominee with account number " + newNominee + " not found");
        }
    }

    @PutMapping("/account/nomineeTransferBalance/{accountNumber}")
    public ResponseEntity<Object> transfer(@PathVariable String accountNumber, @RequestBody String newNominee) {
        Customer existingCustomer = customerRepository.findByAccountNumber(accountNumber);
        Customer existingNominee = customerRepository.findByAccountNumber(newNominee);
        if (existingCustomer != null && existingNominee!=null) {
           boolean NomineeBalTransfer=customerRepository.transferBalance(existingCustomer,existingNominee);
           if(NomineeBalTransfer){
               customerRepository.deleteByAccountNumber(accountNumber);
               return ResponseEntity.ok().body("Succesfully transfered the money to nominee and removed the customer");

           }
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot transfer due to exception");


        } else if(existingCustomer==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with account number " + accountNumber + " not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nominee with account number " + newNominee + " not found");
        }
    }

}
