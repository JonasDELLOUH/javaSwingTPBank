package com.jonas.bancTP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;

public class AddLoanScreen extends  JFrame{
    private JTextField textLoanAmount;
    private JButton saveLoanButton;
    private JPanel jpanelMain;
    BankAccount bankAccount;

    public AddLoanScreen(BankAccount bankAccount) {
        super("Compte");
        this.setContentPane(this.jpanelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.bankAccount = bankAccount;
        this.setVisible(true);
        this.setSize(700, 200);
        saveLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Loan loan = new Loan(bankAccount, ZonedDateTime.now().toString(), Integer.parseInt(textLoanAmount.getText()));
                bankAccount.addALoan(loan);
                AnswerDialog answerDeposit = new AnswerDialog("Le prêt a été effectué avec succès !!!");
                answerDeposit.setVisible(true);
                disposing();

            }
        });
    }

    private void disposing(){
        this.dispose();
        BankAccountScreen bankAccountScreen = new BankAccountScreen(bankAccount);
        bankAccountScreen.setVisible(true);
    }
}
