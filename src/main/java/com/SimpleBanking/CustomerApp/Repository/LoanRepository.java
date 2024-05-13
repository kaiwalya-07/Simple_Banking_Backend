package com.SimpleBanking.CustomerApp.Repository;

import com.SimpleBanking.CustomerApp.Entity.Loan;


public interface LoanRepository {
    Loan createLoan(Loan loan);
}
