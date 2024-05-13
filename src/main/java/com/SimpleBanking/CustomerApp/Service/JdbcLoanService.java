package com.SimpleBanking.CustomerApp.Service;


import com.SimpleBanking.CustomerApp.Entity.Loan;
import com.SimpleBanking.CustomerApp.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JdbcLoanService implements LoanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Loan createLoan(Loan loan) {
        try {
            String loanAccountNumber = generateUniqueLoanAccountNumber();

            loan.setLoanACCNos(loanAccountNumber);
            jdbcTemplate.update("INSERT INTO loan (amount, loan_ac_no, cust_ac_no, interest, duration, MinAmount) VALUES (?, ?, ?, ?, ?, ?)",
                    loan.getAmount(), loan.getLoanACCNos(), loan.getCust_ac_no(), loan.getInterest(), loan.getDuration(), loan.getMinAmount());
            return loan;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Method to generate a unique loan account number
    private String generateUniqueLoanAccountNumber() {

        UUID uuid = UUID.randomUUID();

        String uuidString = uuid.toString().replaceAll("-", "");


        return "LOAN_" + uuidString;
    }

}