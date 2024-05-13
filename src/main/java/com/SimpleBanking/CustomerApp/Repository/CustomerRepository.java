package com.SimpleBanking.CustomerApp.Repository;

import java.util.List;
import com.SimpleBanking.CustomerApp.Entity.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
    void update(Customer customer);
    Customer findById(Long id);
    List<Customer> findByName(String name);
    Customer findByAccountNumber(String accountNumber);
    Long getBalance(String accountNumber);

    Long updateBalance(Customer customer, Long amount);

    boolean addNominee(Customer cust,String NomineeAccount);

    boolean transferBalance(Customer cust,Customer nominee);


    List<Customer> findAll();

    void deleteByAccountNumber(String accountNumber);
}
