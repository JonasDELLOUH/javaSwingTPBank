package com.jonas.bancTP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithDrawalScreen extends JFrame{
    private JTextField textWithdrawalAmount;
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton donTSaveButton;
    private BankAccount bankAccount;

    WithDrawalScreen(BankAccount bankAccount){
        super("Deposit Screen");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(700, 200);
        this.bankAccount = bankAccount;
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(bankAccount.withdrawal(Integer.parseInt(textWithdrawalAmount.getText())) == false){

                }
                else{
                    saveAction();
                }
                saveAction();
            }
        });
        donTSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveAction();
            }
        });
    }

    private void saveAction() {
        this.dispose();
        BankAccountScreen bankAccountScreen = new BankAccountScreen(bankAccount);
    }
}
