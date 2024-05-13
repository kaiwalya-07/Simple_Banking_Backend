package com.SimpleBanking.CustomerApp.Repository;

import com.SimpleBanking.CustomerApp.Entity.Loan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanRowMapper implements RowMapper<Loan> {

    @Override
    public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
        Loan loan=new Loan();
        loan.setAmount(rs.getLong("amount"));
        loan.setLoanACCNos(rs.getString("loan_ac_no"));
        loan.setCust_ac_no(rs.getString("cust_ac_no"));
        loan.setInterest(rs.getFloat("interest"));
        loan.setDuration(rs.getInt("duration"));
        loan.setMinAmount(rs.getInt("MinAmount"));
        return loan;
    }
}
