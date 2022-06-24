package com.jonas.bancTP;

import java.util.Date;

public class Loan {
    BankAccount bankAccount;
    String loanDate;
    int loanAmount;

    public Loan(BankAccount bankAccount, String loanDate, int loanAmount) {
        this.bankAccount = bankAccount;
        this.loanDate = loanDate;
        this.loanAmount = loanAmount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }
}
