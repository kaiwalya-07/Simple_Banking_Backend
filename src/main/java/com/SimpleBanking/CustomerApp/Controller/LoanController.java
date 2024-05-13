package com.SimpleBanking.CustomerApp.Controller;

import com.SimpleBanking.CustomerApp.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.SimpleBanking.CustomerApp.Entity.Customer;
import com.SimpleBanking.CustomerApp.Entity.Loan;
import com.SimpleBanking.CustomerApp.Repository.CustomerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LoanRepository loanRepository;

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody Loan loan) {
        String lc_ac_no=loan.getCust_ac_no();
        Customer existingCustomer = customerRepository.findByAccountNumber(lc_ac_no);
        Long miniAmount= (long) loan.getMinAmount();

        if(existingCustomer!=null) {
            Loan savedLoan = loanRepository.createLoan(loan);
            return ResponseEntity.status(HttpStatus.OK).body(savedLoan);

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Does not exist");
    }

}
