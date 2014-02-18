package exercises.refactored;

import javax.inject.Inject;

import exercises.refactored.textdevice.TextDevice;

/**
 * This is an Observer class to listen to monthly payment computation result, and write the output to a Text based 
 * output.
 *
 * The reason this is used to handle one monthly payment and print out one at a time, vs. to have all the MonthlyPayment computed and
 * store in a collection in memory, is that if the number of term year is large, the number of MonthlyPayment will be large and it could cause the system 
 * to fail due to OutOfMemeoryError. 
 */
public class TextDeviceMonthyPaymentObserver implements AmortizationCalculator.Observer {

    private static final String HEADING_FORMAT = "%1$-20s%2$-20s%3$-20s%4$-20s%5$-20s%6$s\n";

	public static final String MONTHLY_PAYMENT_FORMAT = "%1$-20d%2$-20.2f%3$-20.2f%4$-20.2f%5$-20.2f%6$-20.2f\n";

	private TextDevice textDevice;

	@Inject 
	public TextDeviceMonthyPaymentObserver(TextDevice textDevice) {
		this.textDevice = textDevice;
		textDevice.printf(String.format(HEADING_FORMAT, 	                
				"PaymentNumber", "PaymentAmount", "PaymentInterest","CurrentBalance", "TotalPayments", "TotalInterestPaid"));
	}

	/**
	 * Handles computed MonthlyPayment result, print out to the <code>TextDevice</code>
	 */
	@Override
	public void monthlyComputed(MonthlyPayment monthly) {
        textDevice.printf(MONTHLY_PAYMENT_FORMAT, monthly.getPaymentNumber(),
                ((double) monthly.getPaymentAmount()) / 100d,
                ((double) monthly.getInterestPaying()) / 100d,
                ((double) monthly.getRemaining()) / 100d,
                ((double) monthly.getTotalPayments()) / 100d,
                ((double) monthly.getTotalInterestPaid()) / 100d);

	}

}
