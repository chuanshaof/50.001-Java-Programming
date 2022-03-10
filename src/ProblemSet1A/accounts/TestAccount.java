package ProblemSet1A.accounts;

public class TestAccount{
    public static void main (String[] args) {
        Account account = new Account(1122, 20000);
        Account.setAnnualInterestRate(4.5);

        account.withdraw(2500);
        account.deposit(3000);
        System.out.println("Balance is " + account.getBalance());
        System.out.println("Monthly interest is " +
                account.getMonthlyInterest());

        Account account1 = new Account(1133, 10000);
        account1.withdraw(1000);
        System.out.println(account1.getBalance());
        System.out.println(account1.getMonthlyInterest());

        Account account2 = new Account();
        System.out.println(account2.getBalance());
        System.out.println(account2.getMonthlyInterestRate());

        Account.setAnnualInterestRate(1);
        System.out.println(account2.getMonthlyInterestRate());

    }
}
