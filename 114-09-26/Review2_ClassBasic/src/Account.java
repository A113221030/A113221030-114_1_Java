public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double initialBalance) {
        this.setAccountNumber(accountNumber);
        try{
            this.setBalance(initialBalance);
        }catch(IllegalArgumentException e) {
            System.out.println("初始餘額錯誤:"+ e.getMessage()+",將餘額設為0");
            this.balance =0;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance){
        if(balance > 0){
            this.balance = balance;
        }else{
            throw new IllegalArgumentException("帳戶餘額必須為正數");
        }
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber =accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }


    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount.");
            return false;
        }
    }
}
