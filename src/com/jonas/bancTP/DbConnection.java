package com.jonas.bancTP;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection {
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            System.out.println("Connceted!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e+"");
        }
        return  con;
    }

    public static void insertAccount(BankAccount bankAccount) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO BANKACCOUNT(OwnerName, accountNumber, solde, interest) VALUES(?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, bankAccount.getOwnerName());
            ps.setString(2, bankAccount.getAccountNumber());
            ps.setString(3, Integer.toString(bankAccount.getSolde()));
            ps.setString(4, Double.toString(bankAccount.getInterest()));
            ps.execute();
            System.out.println("Data has been inserted!");
        } catch(SQLException e) {
            System.out.println(e.toString());
            // always remember to close database connections
        } finally {
            try{
                ps.close();
                con.close();
            } catch(SQLException e) {
                System.out.println(e.toString());
            }

        }
    }

    public static ArrayList<BankAccount> selectAllAccount() {
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM BANKACCOUNT";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("ALL ACCOUNTS : \n");
            while (rs.next()) {
                BankAccount bankAccount = new BankAccount(rs.getString("OwnerName"), rs.getString("accountNumber"), Integer.parseInt(rs.getString("solde")));
                bankAccount.setInterest(Double.parseDouble(rs.getString("interest")));
                accounts.add(bankAccount);
                System.out.println("take");

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return accounts;
    }

    public static void updateSolde(BankAccount bankAccount) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE BANKACCOUNT SET solde=? WHERE accountNumber=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, Integer.toString(bankAccount.getSolde()));
            ps.setString(2, bankAccount.getAccountNumber());
            ps.execute();
            System.out.println("Data has been updated!");
        } catch(SQLException e) {
            System.out.println(e.toString());
            // always remember to close database connections
        } finally {
            try{
                ps.close();
                con.close();
            } catch(SQLException e) {
                System.out.println(e.toString());
            }

        }
    }

    public static void updateIsSparingAccount(BankAccount bankAccount) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE BANKACCOUNT SET isNormalAccount=0 WHERE accountNumber=?";
            ps = con.prepareStatement(sql);
            ps.setString(2, bankAccount.getAccountNumber());
            ps.execute();
            System.out.println("Account is now sparing account");
        } catch(SQLException e) {
            System.out.println(e.toString());
            // always remember to close database connections
        } finally {
            try{
                ps.close();
                con.close();
            } catch(SQLException e) {
                System.out.println(e.toString());
            }

        }
    }

    public static void addLoan(Loan loan) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO LOAN(loanAmount, loanDate, bankAccountNumber) VALUES(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, Integer.toString(loan.getLoanAmount()));
            ps.setString(2, loan.getLoanDate());
            ps.setString(3, loan.getBankAccount().getAccountNumber());
            ps.execute();
            System.out.println("Data has been inserted!");
        } catch(SQLException e) {
            System.out.println(e.toString());
            // always remember to close database connections
        } finally {
            try{
                ps.close();
                con.close();
            } catch(SQLException e) {
                System.out.println(e.toString());
            }

        }
    }

    public static ArrayList<Loan> selectAllLoan(BankAccount bankAccount) {
        ArrayList<Loan> loans = new ArrayList<Loan>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM LOAN ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("ALL ACCOUNTS : \n");
            while (rs.next()) {
                Loan loan = new Loan(bankAccount, rs.getString("loanDate"), Integer.parseInt(rs.getString("loanAmount")));
                loans.add(loan);
                System.out.println("take");

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return loans;
    }

    public static void deleteLoan(Loan loan) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM LOAN WHERE loanDate=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, loan.getLoanDate());
            ps.execute();
            System.out.println("Data has been deleted!");
        } catch(SQLException e) {
            System.out.println(e.toString());
            // always remember to close database connections
        } finally {
            try{
                ps.close();
                con.close();
            } catch(SQLException e) {
                System.out.println(e.toString());
            }

        }
    }
}
