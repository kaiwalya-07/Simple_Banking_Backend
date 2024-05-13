package com.SimpleBanking.CustomerApp.Service;

import com.SimpleBanking.CustomerApp.Repository.CustomerRepository;
import com.SimpleBanking.CustomerApp.Repository.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.SimpleBanking.CustomerApp.Entity.Customer;

@Repository
public class JdbcCustomerService implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Customer save(Customer customer) {
        jdbcTemplate.update("INSERT INTO customer (id, name, account_type, account_number, balance) VALUES (?, ?, ?, ?, ?)",
                customer.getId(),customer.getName(), customer.getAccountType(), customer.getAccountNumber(),customer.getBalance());
        return customer;
    }

    public void update(Customer cust) {
//        jdbcTemplate.update("INSERT INTO customer (id, name, account_type, account_number) VALUES (?, ?, ?, ?)",
//                customer.getId(),customer.getName(), customer.getAccountType(), customer.getAccountNumber());
//        return customer;
        try {
            String accountNos=cust.getAccountNumber();
            String newName=cust.getName();
            jdbcTemplate.update("UPDATE customer SET name=?  WHERE account_number=?",newName, accountNos);
        }catch (Exception  ex){
            ex.printStackTrace();
            return ;
        }
    }

    @Override
    public Customer findById(Long id) {
       try {
           return jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id = ?",
                   new Object[]{id}, new CustomerRowMapper());
       }catch (Exception  ex){
           ex.printStackTrace();
           return null;
       }
    }

    @Override
    public List<Customer> findByName(String name) {
        try {
            return jdbcTemplate.query("SELECT * FROM customer WHERE name = ?",
                    new Object[]{name}, new CustomerRowMapper());
        }catch (Exception  ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public Customer findByAccountNumber(String accountNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM customer WHERE account_number = ?",
                    new Object[]{accountNumber}, new CustomerRowMapper());
        }catch (Exception  ex){
            ex.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Long getBalance(String accountNumber) {
//        try {
//            return jdbcTemplate.queryForObject("SELECT balance FROM customer WHERE account_number=?",
//                    new Object[]{accountNumber},
//                  Long.class);
//        }catch(EmptyResultDataAccessException ex){
//            return null;
//        }
//    }

    @Override
    public Long getBalance(String accountNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT balance FROM customer WHERE account_number=?",
                    new Object[]{accountNumber},
                    Long.class);
        }catch (Exception  ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public Long updateBalance(Customer cust, Long amount) {
        try{
            String accNos=cust.getAccountNumber();
            jdbcTemplate.update("UPDATE customer SET balance=? WHERE account_number=?",amount,accNos);
            return amount;
        }catch (Exception  ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addNominee(Customer cust, String NomineeAccount) {
        try{
            String accNos=cust.getAccountNumber();
            jdbcTemplate.update("UPDATE customer SET nomineeAcc=? WHERE account_number=?",NomineeAccount,accNos);
            return true;
        }catch (Exception  ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean transferBalance(Customer cust, Customer nominee) {
        try{
            Long custBal=cust.getBalance();
            Long nomineeNewBal=nominee.getBalance()+custBal;
            String nomineeAcc=nominee.getAccountNumber();
            jdbcTemplate.update("UPDATE customer SET balance=? WHERE account_number=?",nomineeNewBal,nomineeAcc);
            return true;
        }catch (Exception  ex){
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM customer", new CustomerRowMapper());
    }

    @Override
    public void deleteByAccountNumber(String accountNumber) {
        jdbcTemplate.update("DELETE FROM customer WHERE account_number = ?", accountNumber);
    }

}
