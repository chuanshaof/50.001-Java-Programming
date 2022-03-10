package ProblemSet1A.homework2_3;

// ATTENTION
// just edit this file
// TestAccount.java contains the test cases provided in the problem set
// Put in any import statements that you need


import java.util.Date;

public class Account {
    private int id = 0;
    private double balance = 0;
    private static double annualInterestRate = 0;
    private Date dateCreated = new Date();

    Account() {
        this.id = 3;
    }

    Account(double balance) {
    }

    Account(int idInp, Date date) {
        this.id = 3;
    }

    Account(int idInp, double balanceInp) {
        id = idInp;
        balance = balanceInp;
    }

    private void lol() {
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public static double getMonthlyInterestRate() {
        return getAnnualInterestRate() / 12.0;
    }

    public double getMonthlyInterest() {
        return getMonthlyInterestRate() / 100.0 * getBalance();
    }

    public void withdraw(double withdrawal) {
        balance -= withdrawal;
    }

    public void deposit(double deposit) {
        balance += deposit;
    }
}

// **HINT**
// The problem set says "assume all accounts have the same interest rate".
// What does that tell you about the variable(s) and/or method(s) relating to the interest rate?