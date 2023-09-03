package App;

public class BankAccount {
	private String accountNumber;
	private double balance;

	/*
	 * This is an example of a BankAccount class. 
	 * The implementation can be instantiated with an account number and a balance to begin with.
	 * The class has the ability to deposit and withdraw (unless you have insufficient funds).
	 */
	
	public BankAccount(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) throws IllegalArgumentException{
		if(amount <= 0) {
			throw new IllegalArgumentException("You may not deposit 0 or less dollars into your account.");
		}
		
		balance += amount;
	}

	public void withdraw(double amount) throws IllegalStateException {
		if (balance < amount) {
			throw new IllegalStateException("Insufficient funds in your account. Cannot withdraw.");
		}
		balance -= amount;
	}
}
