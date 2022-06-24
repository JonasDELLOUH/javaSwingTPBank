package com.jonas.bancTP;

import org.sqlite.core.DB;

public class BankAccount {
    String OwnerNamee;
    int solde;
    double interest;
    String accountNumber;

    int loanAmount;

    public BankAccount(String ownerNamee, String number,  int solde) {
        OwnerNamee = ownerNamee;
        this.solde = solde;
        this.accountNumber = number;
    }

    public String getOwnerName() {
        return OwnerNamee;
    }

    public void setOwnerName(String ownerNamee) {
        OwnerNamee = ownerNamee;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String number) {
        this.accountNumber = number;
    }

    boolean deposit(int deposit){
        this.solde+=deposit;
        DbConnection.updateSolde(this);
        return true;
    }

    boolean withdrawal(int cost){
        if(cost > this.solde){
            return false;
        }
        this.solde -= cost;
        DbConnection.updateSolde(this);
        return true;
    }

    void addALoan(Loan loan){
        this.solde += loan.getLoanAmount();
        DbConnection.addLoan(loan);
        DbConnection.updateSolde(this);
    }
    boolean payYourLoans(Loan loan){
        if(loan.getLoanAmount() > this.solde){
            return false;
        }
        this.solde -= loan.getLoanAmount();
        DbConnection.deleteLoan(loan);
        DbConnection.updateSolde(this);
        return  true;
    }

    int calculInterest(){
        return 0;
    }


}
