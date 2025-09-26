public class AccountTest {
    public static void main(String[] args){
        Account account1 =new Account("123456",1000.0);

        System.out.printf("帳戶密碼:%s%n初始餘額: %s%n" ,account1.getAccountNumber(),account1.getBalance() );
        account1.deposit(500.0);
        System.out.printf("存款: %s%n目前餘額: %.2f%n", account1.getAccountNumber(),account1.getBalance());
        account1.withdraw(1000.0);
        System.out.printf("提款: %s%n目前餘額: %.2f%n", account1.getAccountNumber(),account1.getBalance());

        try{
            account1.deposit(-100.0);
            System.out.printf("存款:%.2f%n目前餘額:%.2f%n",account1.getAccountNumber(),account1.getBalance());
        }catch (IllegalArgumentException e){
                System.out.println("存款錯誤"+e.getMessage());
        }

        try{
            account1.withdraw(2000.0);
            System.out.printf("提款:%.2f%n目前餘額:%.2f%n",account1.getAccountNumber(),account1.getBalance());
        }catch (IllegalArgumentException e){
            System.out.println("提款錯誤"+e.getMessage());
        }

    }
}
