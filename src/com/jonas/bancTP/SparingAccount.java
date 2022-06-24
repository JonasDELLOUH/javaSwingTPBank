package com.jonas.bancTP;

public class SparingAccount extends  BankAccount{

    public SparingAccount(String ownerNamee, String number, int solde) {
        super(ownerNamee, number, solde);
    }

    @Override
    int calculInterest() {
        return 0;
    }
}
