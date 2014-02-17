package exercises.refactored;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import exercises.refactored.textdevice.TextDevice;
import exercises.refactored.typeconversion.DoubleParser;
import exercises.refactored.typeconversion.IntegerParser;
import exercises.refactored.typeconversion.NumberParser;

// a "view" that handles IO

public class ConsoleLoanView {

	private final TextDevice textDevice;
	private Validator validator;
	
	@Inject
	public ConsoleLoanView(TextDevice textDevice, Validator validator) {
		this.textDevice = textDevice;
		this.validator = validator;
	}


	public Loan getLoanInfo() {

		Double loanAmount = promptForValidDouble("loanAmount", "Please enter the amount you would like to borrow: ");
		Double interestRate = promptForValidDouble("interestRate", "Please enter the annual percentage rate used to repay the loan: ");
		Integer termYears = promptForValidInteger("termYears", "Please enter the term, in years, over which the loan is repaid: " );
		return new Loan(loanAmount, interestRate, termYears);
	}
	
	
	public void printScheduleTable(ScheduleResult result, Loan loan) {
	       String formatString = "%1$-20s%2$-20s%3$-20s%4$-20s%5$-20s%6$s\n";
	       textDevice.printf(formatString,
	                "PaymentNumber", "PaymentAmount", "PaymentInterest",
	                "CurrentBalance", "TotalPayments", "TotalInterestPaid");

	        // output is in dollars
	        formatString = "%1$-20d%2$-20.2f%3$-20.2f%4$-20.2f%5$-20.2f%6$-20.2f\n";
	        textDevice.printf(formatString, 0, 0d, 0d,
	                ((double) loan.getLoanAmount()),
	                 0d, 0d);
	        for(MonthlyPayment monthly: result.getPayments()) {
	            textDevice.printf(formatString, monthly.getPaymentNumber(),
	                    ((double) monthly.getPaymentAmount()) / 100d,
	                    ((double) monthly.getInterestPaying()) / 100d,
	                    ((double) monthly.getRemaining()) / 100d,
	                    ((double) monthly.getTotalPayments()) / 100d,
	                    ((double) monthly.getTotalInterestPaid()) / 100d);
	        }

	}

	private <T> T promptForValidValue(String propertyName, NumberParser<T> numberParser, String prompt) {
		for (;;) {
			T value = numberParser.parse(textDevice.readLine(prompt));
			if (validate(propertyName, value)) {
				return value;
			}
		}
	}

	private Double promptForValidDouble(String propertyName, String prompt) {
		return promptForValidValue(propertyName,  new DoubleParser(), prompt);
	}

	private Integer promptForValidInteger(String propertyName, String prompt) {
		return promptForValidValue(propertyName,  new IntegerParser(), prompt);
	}


	private boolean validate(String propertyName, Object propertyValue) {
		Set<ConstraintViolation<Loan>> violations = validator.validateValue(Loan.class, propertyName, propertyValue);
		if (violations.size() == 0) {
			return true;
		}
		textDevice.printf("Invalid input for %s\n", propertyName);
		for (ConstraintViolation<Loan> violation : violations) {
			textDevice.printf("    " + violation.getMessage() + "\n");
		}
		return false;
	}

}
