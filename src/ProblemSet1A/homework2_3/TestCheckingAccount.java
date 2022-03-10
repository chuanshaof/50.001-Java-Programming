package ProblemSet1A.homework2_3;

// **ATTENTION**
// YOU DO NOT NEED TO EDIT THIS FILE

import java.util.Date;

public class TestCheckingAccount {

    public static void main(String[] args) {

        CheckingAccount myCheckAcc = new CheckingAccount(1024, 8000.0);
        myCheckAcc.deposit(2000);
        myCheckAcc.withdraw(15000);

        System.out.println(myCheckAcc.getBalance());
        myCheckAcc.withdraw(200);
        System.out.println(myCheckAcc.getBalance());
        myCheckAcc.deposit(7000);
        myCheckAcc.withdraw(200);
        System.out.println(myCheckAcc.getBalance());

        CheckingAccount test1 = new CheckingAccount(0, new Date());
        System.out.println(test1.getBalance());
        System.out.println(test1.getId());

        CheckingAccount test2 = new CheckingAccount(2);
        System.out.println(test2.getBalance());
        System.out.println(test2.getId());

        CheckingAccount test3 = new CheckingAccount();
        System.out.println(test3.getBalance());
        System.out.println(test3.getId());
    }

}
