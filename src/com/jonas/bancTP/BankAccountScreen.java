package com.jonas.bancTP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccountScreen extends JFrame{
    private JLabel bankType;
    private JLabel accountNumber;
    private JLabel owner;
    private JLabel solde;
    private JLabel interest;
    private JButton depositButton;
    private JButton addALoanButton;
    private JButton payYourLoansButton;
    private JButton withdrawalButton;
    private JPanel jpanelMain;
    private JButton comingMenuButton;

    public BankAccountScreen(BankAccount bankAccount){
        super("Compte");
        this.setContentPane(this.jpanelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(500, 500);
        owner.setText(bankAccount.getOwnerName());
        accountNumber.setText(bankAccount.getAccountNumber());
        solde.setText(Integer.toString(bankAccount.getSolde()));
        interest.setText(Double.toString(bankAccount.getInterest()));

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DepositScreen depositScreen = new DepositScreen(bankAccount);
                depositScreen.setVisible(true);
                disposing();
            }
        });
        withdrawalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WithDrawalScreen withDrawalScreen = new WithDrawalScreen(bankAccount);
                withDrawalScreen.setVisible(true);
                disposing();
            }
        });
        addALoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddLoanScreen addLoanScreen  = new AddLoanScreen(bankAccount);
                addLoanScreen.setVisible(true);
                disposing();
            }
        });
        payYourLoansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PayLoanScreen payLoanScreen = new PayLoanScreen(bankAccount);
                payLoanScreen.setVisible(true);
                disposing();
            }
        });
        comingMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                comingAction();
            }
        });
    }
    private void disposing(){
        this.dispose();
    }

    private void comingAction(){
        BankMenuScreen bankMenuScreen = new BankMenuScreen();
        bankMenuScreen.setVisible(true);
        disposing();
    }
}
