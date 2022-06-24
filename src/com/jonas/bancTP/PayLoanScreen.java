package com.jonas.bancTP;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PayLoanScreen extends  JFrame{
    private JList listLoans;
    private JTextField textLoanAmount;
    private JTextField textLoanDate;
    private JPanel jpanelMain;
    private JButton buttonComing;
    private JButton rembourserButton;

    private DefaultListModel listAccountModel;

    private  BankAccount bankAccount;


    private ArrayList<Loan> loans;

    PayLoanScreen(BankAccount bankAccount){
        super("Deposit Screen");
        this.setContentPane(this.jpanelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(700, 500);
        this.bankAccount = bankAccount;
        textLoanAmount.setEnabled(false);
        textLoanDate.setEnabled(false);
        loans = new ArrayList<>();
        loans = DbConnection.selectAllLoan(bankAccount);
        listAccountModel = new DefaultListModel();
        listLoans.setModel(listAccountModel);
        this.refreshLoanlist();
        rembourserButton.setEnabled(false);

        buttonComing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                disposing();
                BankAccountScreen bankAccountScreen = new BankAccountScreen(bankAccount);
                bankAccountScreen.setVisible(true);
            }
        });
        rembourserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int accountNumber = listLoans.getSelectedIndex();
                if (accountNumber >= 0) {
                    Loan loan = loans.get(accountNumber);
                    if( bankAccount.payYourLoans(loan) == false){

                    }
                    else {
                        refreshLoanlist();
                        disposing();
                        PayLoanScreen payLoanScreen = new PayLoanScreen(bankAccount);
                        payLoanScreen.setVisible(true);
                    }
                }

            }
        });
        listLoans.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                listAccountSelection(listSelectionEvent);
            }
        });
    }

    public void refreshLoanlist() {
        listAccountModel.removeAllElements();
        System.out.println("Removing all loans from list");
        loans = DbConnection.selectAllLoan(bankAccount);
        for (Loan loan : loans) {
            System.out.println("Adding loan to list: " + loan.getBankAccount().getOwnerName());
            listAccountModel.addElement(loan.getLoanDate());
        }
    }

    public void listAccountSelection(ListSelectionEvent e){
        int loanNumber = listLoans.getSelectedIndex();
        if (loanNumber >= 0) {
            Loan loan = loans.get(loanNumber);
            textLoanDate.setText(loan.getLoanDate());
            textLoanAmount.setText(Integer.toString(loan.getLoanAmount()));
            rembourserButton.setEnabled(true);
        } else {
            rembourserButton.setEnabled(false);
        }
    }

    private void disposing(){
        this.dispose();
    }

}
