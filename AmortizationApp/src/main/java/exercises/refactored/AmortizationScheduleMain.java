package exercises.refactored;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This application allows a user enter loan information, then it will print out the amortization schedule table.
 * 
 */
public class AmortizationScheduleMain {
	private static Console console = System.console();
	private static Logger logger = LoggerFactory.getLogger(AmortizationScheduleMain.class);
	private static final double[] borrowAmountRange = new double[] { 0.01d, 1000000000000d };
	private static final double[] aprRange = new double[] { 0.000001d, 100d };
	private static final int[] termRange = new int[] { 1, 1000000 };

	public static void main(String[] args) {
		try {
			Loan loan = retrieveLoanFromInput();
			AmortizationCalculator calc = new AmortizationCalculator(loan);
			ScheduleResult result = calc.getSchedule();
			printScheduleTable(result, loan);
		} catch (Exception e) {
			logger.warn("Error occured", e);
		}
	}

	private static void printScheduleTable(ScheduleResult result, Loan loan) {
	       String formatString = "%1$-20s%2$-20s%3$-20s%4$-20s%5$-20s%6$s\n";
	        printf(formatString,
	                "PaymentNumber", "PaymentAmount", "PaymentInterest",
	                "CurrentBalance", "TotalPayments", "TotalInterestPaid");

	        // output is in dollars
	        formatString = "%1$-20d%2$-20.2f%3$-20.2f%4$-20.2f%5$-20.2f%6$-20.2f\n";
	        printf(formatString, 0, 0d, 0d,
	                ((double) loan.getLoanAmount()),
	                 0d, 0d);
	        for(MonthlyPayment monthly: result.getPayments()) {
	            printf(formatString, monthly.getPaymentNumber(),
	                    ((double) monthly.getPaymentAmount()) / 100d,
	                    ((double) monthly.getInterestPaying()) / 100d,
	                    ((double) monthly.getRemaining()) / 100d,
	                    ((double) monthly.getTotalPayments()) / 100d,
	                    ((double) monthly.getTotalInterestPaid()) / 100d);
	        }

	}

    private static void printf(String formatString, Object... args) {

        try {
            if (console != null) {
                console.printf(formatString, args);
            } else {
                System.out.print(String.format(formatString, args));
            }
        } catch (IllegalFormatException e) {
            System.err.print("Error printing...\n");
        }
    }

    private static void print(String s) {
        printf("%s", s);
    }
	private static String readLine(String userPrompt) throws IOException {
		String line = "";

		if (console != null) {
			line = console.readLine(userPrompt);
		} else {
			// print("console is null\n");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			print(userPrompt);
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}

	private static Loan retrieveLoanFromInput() throws IOException {
		String[] userPrompts = { "Please enter the amount you would like to borrow: ", "Please enter the annual percentage rate used to repay the loan: ",
				"Please enter the term, in years, over which the loan is repaid: " };

		String line = "";
		double amount = 0;
		double apr = 0;
		int years = 0;
		for (int i = 0; i < userPrompts.length;) {
			String userPrompt = userPrompts[i];
			line = readLine(userPrompt);
			boolean isValidValue = true;
			try {
				switch (i) {
				case 0:
					amount = Double.parseDouble(line);
					if (isValidBorrowAmount(amount) == false) {
						isValidValue = false;
						double range[] = getBorrowAmountRange();
						print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
					}
					break;
				case 1:
					apr = Double.parseDouble(line);
					if (isValidAPRValue(apr) == false) {
						isValidValue = false;
						double range[] = getAPRRange();
						print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
					}
					break;
				case 2:
					years = Integer.parseInt(line);
					if (isValidTerm(years) == false) {
						isValidValue = false;
						int range[] = getTermRange();
						print("Please enter a positive integer value between " + range[0] + " and " + range[1] + ". ");
					}
					break;
				}
			} catch (NumberFormatException e) {
				isValidValue = false;
			}
			if (isValidValue) {
				i++;
			} else {
				print("An invalid value was entered.\n");
			}
		}
		
		return new Loan(amount, apr, years);
	}

	public static boolean isValidBorrowAmount(double amount) {
		double range[] = getBorrowAmountRange();
		return ((range[0] <= amount) && (amount <= range[1]));
	}

	public static boolean isValidAPRValue(double rate) {
		double range[] = getAPRRange();
		return ((range[0] <= rate) && (rate <= range[1]));
	}

	public static boolean isValidTerm(int years) {
		int range[] = getTermRange();
		return ((range[0] <= years) && (years <= range[1]));
	}

	public static final double[] getBorrowAmountRange() {
		return borrowAmountRange;
	}

	public static final double[] getAPRRange() {
		return aprRange;
	}

	public static final int[] getTermRange() {
		return termRange;
	}

}
