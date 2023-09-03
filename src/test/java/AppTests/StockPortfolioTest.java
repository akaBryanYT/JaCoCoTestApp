package AppTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import App.StockPortfolio;

/*
 * This test class includes test cases for the buyStock(), sellStock(), getHoldings(), and getCashBalance() methods 
 * of the StockPortfolio class. 
 * It also includes several edge cases, such as attempting to buy or sell stocks with invalid quantities or prices, 
 * or with insufficient funds or shares.
 */

public class StockPortfolioTest {

	private StockPortfolio portfolio;
	private double epsilon = 0.0001;

	@BeforeEach
    public void setUp() {
        portfolio = new StockPortfolio();
    }
    
    @Test
    public void testBuyStock() {
        portfolio.depositCash(3000.0);
        portfolio.buyStock("AAPL", 10, 150.0);
        Assertions.assertEquals(10, portfolio.getHoldings("AAPL"));
        Assertions.assertEquals(1500.0, portfolio.getCashBalance(), epsilon);
    }
    
    @Test
    public void testSellStock() {
        portfolio.depositCash(3000.0);
        portfolio.buyStock("AAPL", 10, 150.0);
        portfolio.sellStock("AAPL", 5, 200.0);
        Assertions.assertEquals(5, portfolio.getHoldings("AAPL"));
        Assertions.assertEquals(2500.0, portfolio.getCashBalance(), epsilon);
    }
    
    @Test
    public void testGetHoldings() {
    	portfolio.depositCash(3000);
    	Assertions.assertEquals(0, portfolio.getHoldings("AAPL"));
        portfolio.buyStock("AAPL", 10, 150.0);
        Assertions.assertEquals(10, portfolio.getHoldings("AAPL"));
    }
    
    @Test
    public void testGetCashBalance() {
    	Assertions.assertEquals(0.0, portfolio.getCashBalance(), epsilon);
        portfolio.depositCash(1000.0);
        Assertions.assertEquals(1000.0, portfolio.getCashBalance(), epsilon);
    }
    
    @Test
    public void testDepositCash() {
        portfolio.depositCash(1000.0);
        Assertions.assertEquals(1000.0, portfolio.getCashBalance(), epsilon);
    }
    
    @Test
    public void testInvalidDepositCash() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> portfolio.depositCash(-1000.0));
    	Assertions.assertEquals(0.0, portfolio.getCashBalance(), epsilon);
    }
    
    @Test
    public void testInvalidBuyStock() {
        portfolio.depositCash(3000.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> portfolio.buyStock("AAPL", 0, 150.0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> portfolio.buyStock("AAPL", 10, 0.0));
    }
    
    @Test
    public void testInvalidSellStock() {
    	portfolio.depositCash(3000.0);
    	Assertions.assertThrows(IllegalArgumentException.class, ()->portfolio.sellStock("AAPL", 0, 150.0));
    	Assertions.assertThrows(IllegalArgumentException.class, ()->portfolio.sellStock("AAPL", 12, 0));
    }
    
    @Test
    public void testInsufficientFunds() {
    	Assertions.assertThrows(IllegalStateException.class, () -> portfolio.buyStock("AAPL", 10, 150.0));
    }
    
    @Test
    public void testInsufficientShares() {
        portfolio.depositCash(3000.0);
        portfolio.buyStock("AAPL", 10, 150.0);
        Assertions.assertThrows(IllegalStateException.class, () -> portfolio.sellStock("AAPL", 15, 200.0));
    }
}
