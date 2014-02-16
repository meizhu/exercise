package exercises.refactored;

import java.util.List;

/**
 * This is a PoJo containing the entire amortization schedule calculated result
 * 
 */
public class ScheduleResult {
	private double monthlyAmount=0.0;
	private int numberOfPayments;
	private List<MonthlyPayment> payments;
	private long totalInterestPaid = 0;
	private long totalPayments = 0;
	public double getMonthlyAmount() {
		return monthlyAmount;
	}
	public int getNumberOfPayments() {
		return numberOfPayments;
	}
	public List<MonthlyPayment> getPayments() {
		return payments;
	}
	public long getTotalInterestPaid() {
		return totalInterestPaid;
	}
	
	public long getTotalPayments() {
		return totalPayments;
	}
	public void setMonthlyAmount(double monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}
	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}
	public void setPayments(List<MonthlyPayment> payments) {
		this.payments = payments;
	}
	public void setTotalInterestPaid(long totalInterestPaid) {
		this.totalInterestPaid = totalInterestPaid;
	}
	public void setTotalPayments(long totalPayments) {
		this.totalPayments = totalPayments;
	}

}
