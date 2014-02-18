package exercises.refactored;

/**
 * An instance of this class captures one monthly payment details. All the fields are in cents.
 *  
 */
public class MonthlyPayment {
	private int paymentNumber;
	private long paymentAmount;
	private long interestPaying;
	private long principalPaying;
	private long totalPayments;
	private long totalInterestPaid;
	private long remaining;
	public int getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public long getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public long getInterestPaying() {
		return interestPaying;
	}
	public void setInterestPaying(long interestPaying) {
		this.interestPaying = interestPaying;
	}
	public long getPrincipalPaying() {
		return principalPaying;
	}
	public void setPrincipalPaying(long principalPaying) {
		this.principalPaying = principalPaying;
	}
	public long getTotalPayments() {
		return totalPayments;
	}
	public void setTotalPayments(long totalPayments) {
		this.totalPayments = totalPayments;
	}
	public long getTotalInterestPaid() {
		return totalInterestPaid;
	}
	public void setTotalInterestPaid(long totalInterestPaid) {
		this.totalInterestPaid = totalInterestPaid;
	}
	public long getRemaining() {
		return remaining;
	}
	public void setRemaining(long remaining) {
		this.remaining = remaining;
	}
	
	

}
