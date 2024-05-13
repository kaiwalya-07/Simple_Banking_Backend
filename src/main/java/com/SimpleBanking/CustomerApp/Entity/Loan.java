package com.SimpleBanking.CustomerApp.Entity;

public class Loan {
    private Long amount;
    private String loanACCNos; // Changed variable name to match setter/getter
    private String cust_ac_no; // Changed variable name to match setter/getter
    private Float interest;
    private int duration;
    private int MinAmount;

    // Constructor with all fields except loanACCNos
    public Loan(Long amount, String customerAcc, Float interest, int duration, int minAmount) {
        this.amount = amount;
        this.cust_ac_no = customerAcc;
        this.interest = interest;
        this.duration = duration;
        MinAmount = minAmount;
    }

    public Loan() {

    }

    // Getter and Setter methods
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getLoanACCNos() {
        return loanACCNos;
    }

    public void setLoanACCNos(String loanACCNos) {
        this.loanACCNos = loanACCNos;
    }

    public String getCust_ac_no() {
        return cust_ac_no;
    }

    public void setCust_ac_no(String cust_ac_no) {
        this.cust_ac_no = cust_ac_no;
    }

    public Float getInterest() {
        return interest;
    }

    public void setInterest(Float interest) {
        this.interest = interest;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMinAmount() {
        return MinAmount;
    }

    public void setMinAmount(int minAmount) {
        MinAmount = minAmount;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", loanACCNos='" + loanACCNos + '\'' +
                ", customerAcc='" + cust_ac_no + '\'' +
                ", interest=" + interest +
                ", duration=" + duration +
                ", MinAmount=" + MinAmount +
                '}';
    }
}
