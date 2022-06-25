package com.jonas.bancTP;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankMenuScreen extends JFrame{
    private JList listAccount;
    private JTextField textOwnerName;
    private JTextField textAccountNumber;
    private JTextField textSolde;
    private JTextField textInterst;
    private JButton newButton;
    private JButton editButton;
    private JPanel mainPanel;
    private ArrayList<BankAccount> accounts;
    private DefaultListModel listAccountModel;

    public BankMenuScreen() {
        super("BOA Menu");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(600,500);
        textOwnerName.setEnabled(false);
        textAccountNumber.setEnabled(false);
        textSolde.setEnabled(false);
        textInterst.setEnabled(false);
        accounts = new ArrayList<BankAccount>();
        accounts = DbConnection.selectAllAccount();
        listAccountModel = new DefaultListModel();
        listAccount.setModel(listAccountModel);
        this.refreshAccountlist();
        editButton.setEnabled(false);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //buttonNewClick(actionEvent);
                CreateAccount createAccount = new CreateAccount();
                createAccount.setVisible(true);
                disposing();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonEditClick(actionEvent);
            }
        });

        listAccount.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                listAccountSelection(listSelectionEvent);
            }
        });
    }

    public void buttonNewClick(ActionEvent e){
        BankAccount bankAccount = new BankAccount(
                textOwnerName.getText(),
                textAccountNumber.getText(),
                Integer.parseInt(textSolde.getText())
        );
        accounts.add(bankAccount);
        refreshAccountlist();
    }

    private void buttonEditClick(ActionEvent e){
        int accountNumber = listAccount.getSelectedIndex();
        if (accountNumber >= 0) {

            BankAccount bankAccount = accounts.get(accountNumber);
            BankAccountScreen bankAccountScreen = new BankAccountScreen(bankAccount);
            bankAccountScreen.setVisible(true);
            this.dispose();
//            bankAccount.setOwnerName(textOwnerName.getText());
//            bankAccount.setAccountNumber(textAccountNumber.getText());
//            bankAccount.setSolde(Integer.parseInt(textSolde.getText()));
//            bankAccount.setInterest(Double.parseDouble(textInterst.getText()));
            refreshAccountlist();
        }
        disposing();
    }

    public void listAccountSelection(ListSelectionEvent e){
        int personNumber = listAccount.getSelectedIndex();
        if (personNumber >= 0) {
            BankAccount bankAccount = accounts.get(personNumber);
            textOwnerName.setText(bankAccount.getOwnerName());
            textAccountNumber.setText(bankAccount.getAccountNumber());
            textSolde.setText(String.valueOf(bankAccount.getSolde()));
            textInterst.setText(String.valueOf(bankAccount.getInterest()));
            editButton.setEnabled(true);
        } else {
            editButton.setEnabled(false);
        }
    }
    public void refreshAccountlist() {
        listAccountModel.removeAllElements();
        System.out.println("Removing all account from list");
        accounts = DbConnection.selectAllAccount();
        for (BankAccount bankAccount : accounts) {
            System.out.println("Adding account to list: " + bankAccount.getOwnerName());
            listAccountModel.addElement(bankAccount.getOwnerName());
        }
    }

    public void addAccount(BankAccount bankAccount) {
        DbConnection.insertAccount(bankAccount);
        refreshAccountlist();
    }

    private void disposing(){
        this.dispose();
    }
    
    

    public static void main(String[] args){
        BankMenuScreen bankMenuScreen = new BankMenuScreen();
        bankMenuScreen.setVisible(true);

        NormalAccount n1 = new NormalAccount("Jonas", "00012365", 0);
        NormalAccount n2 = new NormalAccount("Jean", "00012006", 100);
        NormalAccount n3 = new NormalAccount("Pierre", "00012005", 500);

    }
}
