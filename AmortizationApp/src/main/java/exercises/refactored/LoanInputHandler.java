package exercises.refactored;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import exercises.refactored.textdevice.TextDevice;
import exercises.refactored.typeconversion.DoubleParser;
import exercises.refactored.typeconversion.IntegerParser;
import exercises.refactored.typeconversion.NumberParser;


/**
 * 
 * This class handles prompting user for input of loan information. It will validate the input and parse into corresponding numeric types.
 *
 */
public class LoanInputHandler {

	private final TextDevice textDevice;
	private Validator validator;

	/**
	 * Creates an instance of LoanInputHandler to collect user input loan info.
	 * 
	 * @param textDevice - the input device is used.
	 * @param validator - The validator is used to verify the input values.
	 */
	@Inject
	public LoanInputHandler(TextDevice textDevice, Validator validator) {
		this.textDevice = textDevice;
		this.validator = validator;
	}

	/**
	 * Calling this method, it will prompt user to enter input data.
	 * 
	 * @return
	 */
	public Loan getLoanInfo() {

		Double loanAmount = promptForValidDouble("loanAmount", "Please enter the amount you would like to borrow: ");
		Double interestRate = promptForValidDouble("interestRate", "Please enter the annual percentage rate used to repay the loan: ");
		Integer termYears = promptForValidInteger("termYears", "Please enter the term, in years, over which the loan is repaid: ");
		return new Loan(loanAmount, interestRate, termYears);
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
		return promptForValidValue(propertyName, new DoubleParser(), prompt);
	}

	private Integer promptForValidInteger(String propertyName, String prompt) {
		return promptForValidValue(propertyName, new IntegerParser(), prompt);
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
