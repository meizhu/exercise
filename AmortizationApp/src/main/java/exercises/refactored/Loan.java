package exercises.refactored;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * User: mzhu
 * Date: 2/13/14 10:06 PM
 */
public class Loan {
	
//	private static final double[] borrowAmountRange = new double[] { 0.01d, 1000000000000d };
//	private static final double[] aprRange = new double[] { 0.000001d, 100d };
//	private static final int[] termRange = new int[] { 1, 1000000 };


	@DecimalMin("0.01") 
	@DecimalMax("1000000000000")
	private final Double loanAmount;
	
	@DecimalMin("0.000001") 
	@DecimalMax("100")
	private final Double interestRate;
	
	@Min(1)
	@Max(1000000)
	private final Integer termYears;
	
	public Loan(Double amount, Double rate, Integer term) {
		this.loanAmount = amount;
		this.interestRate = rate;
		this.termYears = term;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public Double getInterestRate() {
		return interestRate;
	}
	public Integer getTermYears() {
		return termYears;
	}
	
	
}
