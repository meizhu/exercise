package exercises.refactored;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * This is a bean class which wraps information for a loan. The fields are annotated with validation rules. 
 * The bean can be validated as a whole or each individual fields can be validated, as it is used 
 * by  <code>LoanInputHandler</code>. This allows the validation rules to be authored in one location. 
 *  
 */
public class Loan {

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
