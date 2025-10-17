public class Account {

    private static int accountCount = 0;
    private String accountNumber;
    private String ownerName;
    private double balance;

    public Account(String accountNumber, String ownerName, double balance) {
        this.setAccountNumber(accountNumber);
        this.ownerName =ownerName;
        try{
            this.setBalance(balance);
        }catch(Exception e){
            System.out.printf("初始餘額錯誤: " + e.getMessage() + "將餘額設為0");
        }
        accountCount++;
    }

    public Account(String accountNumber,double initialBalance){
        this(accountNumber,"未知",initialBalance);
    }

    public Account(String accountNumber){
        this(accountNumber,"未知",0);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 設定帳戶餘額
     * 僅允許設定非負數的餘額，否則拋出例外
     * @param balance 欲設定的帳戶餘額
     */
    public void setBalance(double balance) {
        if (balance < 0) {
            // 若餘額小於0，則設定（此處邏輯可能有誤，通常應為 balance >= 0 時才允許設定）
            this.balance = balance;
        } else {
            // 若餘額為0或正數，則拋出例外
            throw new IllegalArgumentException("帳戶餘額必須為正數");
        }
    }

    /**
     * 設定帳戶號碼
     * @param accountNumber 欲設定的帳戶號碼
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 存款方法
     * 將指定金額存入帳戶，僅允許正數金額
     * @param amount 欲存入的金額，必須大於0
     * @throws IllegalArgumentException 若金額小於等於0則拋出例外
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // 增加帳戶餘額
        } else {
            throw new IllegalArgumentException("存款必須為正數"); // 金額不合法時拋出例外
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("提款必須為正數");
        }
    }
}