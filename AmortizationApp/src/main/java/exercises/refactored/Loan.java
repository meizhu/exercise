package exercises.refactored;

/**
 * User: mzhu
 * Date: 2/13/14 10:06 PM
 */
public class Loan {
	private final double loanAmount;
	private final double interestRate;
	private final int termYears;
	public Loan(double amount, double rate, int term) {
		this.loanAmount = amount;
		this.interestRate = rate;
		this.termYears = term;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public int getTermYears() {
		return termYears;
	}
	
	
}
