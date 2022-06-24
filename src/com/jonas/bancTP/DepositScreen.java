package com.jonas.bancTP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositScreen extends JFrame {
    private JTextField textDepositAmount;
    private JButton saveButton;
    private JButton donTSaveButton;
    private JPanel mainPanel;
    BankAccount bankAccount;

    DepositScreen(BankAccount bankAccount) {
        super("Deposit Screen");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.bankAccount = bankAccount;
        this.setSize(700, 200);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bankAccount.deposit(Integer.parseInt(textDepositAmount.getText()));
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
