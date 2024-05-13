package com.SimpleBanking.CustomerApp.Repository;
import org.springframework.jdbc.core.RowMapper;
import com.SimpleBanking.CustomerApp.Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setAccountType(rs.getString("account_type"));
        customer.setAccountNumber(rs.getString("account_number"));
        customer.setBalance(rs.getLong("balance"));
        return customer;
    }
}

