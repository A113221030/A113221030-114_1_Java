import java.util.Scanner;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        setBalance(balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);
        while (balance < 0 && attempts < 3) {
            System.out.println("餘額必須為正數，請重新輸入：");
            balance = scanner.nextDouble();
            attempts++;
        }
        if (balance < 0) {
            throw new IllegalArgumentException("餘額必須為正數");
        }
        this.balance = balance;
    }

    public void deposit(double amount) {
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);
        while (amount <= 0 && attempts < 3) {
            System.out.println("存款金額必須為正數，請重新輸入：");
            amount = scanner.nextDouble();
            attempts++;
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("存款金額必須為正數");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);
        while ((amount <= 0 || amount > balance) && attempts < 3) {
            System.out.println("提款金額必須為正數且小於等於餘額，請重新輸入：");
            amount = scanner.nextDouble();
            attempts++;
        }
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("提款金額必須為正數且小於等於餘額");
        }
        this.balance -= amount;
    }
}
