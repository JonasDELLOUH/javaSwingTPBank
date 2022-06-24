package com.jonas.bancTP;

public class NormalAccount extends  BankAccount{

    public NormalAccount(String ownerName, String number,  int solde) {
        super(ownerName, number, solde);
    }

    @Override
    int calculInterest() {
        return 0;
    }

}
