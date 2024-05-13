package com.SimpleBanking.CustomerApp.Entity;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Customer {
    private Long id;
    private String name;
    private String accountType;
    private String accountNumber;
    private Long balance;
    private String nomineeAcc;


    public Customer(Long id, String name, String accountType, String accountNumber, Long balance) {
        this.id = id;
        this.name = name;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;

    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getNomineeAcc() {
        return nomineeAcc;
    }

    public void setNomineeAcc(String nomineeAcc) {
        this.nomineeAcc = nomineeAcc;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", nomineeAcc='" + nomineeAcc + '\'' +
                '}';
    }
}
