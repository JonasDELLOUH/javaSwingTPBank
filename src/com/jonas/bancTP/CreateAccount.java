package com.jonas.bancTP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount extends  JFrame{
    private JPanel panelMain;
    private JTextField textOwnerName;
    private JTextField textAccountNumber;
    private JTextField textSolde;
    private JTextField textInterst;
    private JComboBox AccountTypeComboBox;
    private JButton saveAccountButton;
    private JButton donTSaveButton;

    public CreateAccount() {
        super("Créaton de Compte");
        AccountTypeComboBox.addItem("Normal Account");
        AccountTypeComboBox.addItem("Sparing Account");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        textInterst.setEnabled(false);
        this.setSize(500, 500);

        saveAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(AccountTypeComboBox.getSelectedItem() == "Normal Account"){
                    NormalAccount normalAccount = new NormalAccount(textOwnerName.getText(), textAccountNumber.getText(), Integer.parseInt(textSolde.getText()));
                    normalAccount.setInterest(0);
                    DbConnection.insertAccount(normalAccount);
                    System.out.println("create Normal Account");
                }
                else{
                    SparingAccount sparingAccount = new SparingAccount(textOwnerName.getText(), textAccountNumber.getText(), Integer.parseInt(textSolde.getText()));
                    sparingAccount.setInterest(Double.parseDouble(textInterst.getText()));
                    DbConnection.insertAccount(sparingAccount);
                    System.out.println("create Sparing Account");
                }
                disposing();
            }
        });
        donTSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                disposing();
            }
        });
    }

    private void disposing(){
        this.dispose();
        BankMenuScreen bankMenuScreen = new BankMenuScreen();
        bankMenuScreen.setVisible(true);
    }
}
