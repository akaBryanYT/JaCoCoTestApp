package App;

import java.util.HashMap;

/*
 * The StockPortfolio class represents a portfolio of stocks held by an investor. 
 * It has two main data members: holdings, which is a HashMap that associates stock symbols with the number of shares held, 
 * and cashBalance, which represents the amount of cash available to the investor for buying additional stocks.
 
 * The class has several methods for buying and selling stocks, as well as for querying the current holdings and cash balance. 
 * The buyStock method takes a stock symbol, a quantity, and a price per share, and deducts the total cost of 
 * the purchase from the cash balance.The sellStock method takes the same parameters, but adds the total proceeds of 
 * the sale to the cash balance. Both methods throw exceptions if the provided quantity or price is invalid, 
 * or if the transaction would result in insufficient funds or shares.
 */

public class StockPortfolio {

	private HashMap<String, Integer> holdings;
	private double cashBalance;

	public StockPortfolio() {
		holdings = new HashMap<>();
		cashBalance = 0.0;
	}

	public void buyStock(String symbol, int quantity, double pricePerShare)
			throws IllegalArgumentException, IllegalStateException {
		if (quantity <= 0 || pricePerShare <= 0.0) {
			throw new IllegalArgumentException("Invalid quantity or price");
		}

		double totalCost = quantity * pricePerShare;
		if (totalCost > cashBalance) {
			throw new IllegalStateException("Insufficient funds");
		}

		int currentQuantity = holdings.getOrDefault(symbol, 0);
		holdings.put(symbol, currentQuantity + quantity);
		cashBalance -= totalCost;
	}

	public void sellStock(String symbol, int quantity, double pricePerShare)
			throws IllegalArgumentException, IllegalStateException {
		if (quantity <= 0 || pricePerShare <= 0.0) {
			throw new IllegalArgumentException("Invalid quantity or price");
		}

		int currentQuantity = holdings.getOrDefault(symbol, 0);
		if (quantity > currentQuantity) {
			throw new IllegalStateException("Insufficient shares");
		}

		double totalProceeds = quantity * pricePerShare;
		holdings.put(symbol, currentQuantity - quantity);
		cashBalance += totalProceeds;
	}

	public int getHoldings(String symbol) {
		return holdings.getOrDefault(symbol, 0);
	}

	public double getCashBalance() {
		return cashBalance;
	}

	public void depositCash(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Cannot deposit 0 or less dollars.");
		}
		
		cashBalance += amount;
	}
}
