package ProblemSet1A.homework2_3;

// **ATTENTION**
// Edit just this file to submit your answer
// You need not edit the TestCheckingAccount.java file
// Leave the Account.class file alone
// If your class has problems accessing the Account.class,
// go to Actions -> Reset Assignment. Make sure you have your code stored on your computer.

import java.util.Date;

public class CheckingAccount extends Account {
    private static double overdraft = -5000;

    CheckingAccount() {
        this(2);
    }

    CheckingAccount(double balance) {
        super(balance);
        this.setBalance(100);
    }

    // @Override
    private void lol() {
        System.out.println("test1");
    }

    CheckingAccount(int idInp, Date date) {

    }


    CheckingAccount(int id, double balance) {
        this.setId(id);
        this.setBalance(balance);
    }

    public void withdraw(double withdrawal) {
        if (this.getBalance() - withdrawal < overdraft) {
            System.out.println("over limit");
        } else {
            super.withdraw(withdrawal);
        }
    }
}

