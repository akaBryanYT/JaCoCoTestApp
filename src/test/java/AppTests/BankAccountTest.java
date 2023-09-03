package AppTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import App.BankAccount;

public class BankAccountTest {

	private double epsilon = 0.001;
	private BankAccount account;

	@BeforeEach
	public void setup() {
		account = new BankAccount("123456789", 100.0);
	}
	
	@Test
	public void testGetAccountNumber() {
		
		Assertions.assertEquals("123456789", account.getAccountNumber());
	}

	@Test
	public void testGetBalance() {
		Assertions.assertEquals(100.0, account.getBalance(), epsilon);
	}

	@Test
	public void testDeposit() throws IllegalArgumentException{
		account.deposit(50.0);
		Assertions.assertEquals(150.0, account.getBalance(), epsilon);
	}
	
	@Test public void testIllegalDeposit() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(-23.01));
	}

	@Test
	public void testWithdraw() throws IllegalStateException {
		account.withdraw(50.0);
		Assertions.assertEquals(50.0, account.getBalance(), epsilon);
	}

	@Test
	public void testWithdrawInsufficientFunds() {
		Assertions.assertThrows(IllegalStateException.class, () -> account.withdraw(150.0));
	}
}
