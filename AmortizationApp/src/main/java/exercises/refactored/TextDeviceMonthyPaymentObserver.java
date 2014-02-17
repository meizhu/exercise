package exercises.refactored;

import javax.inject.Inject;

import exercises.refactored.textdevice.TextDevice;

public class TextDeviceMonthyPaymentObserver implements AmortizationCalculator.Observer {

    private static final String HEADING_FORMAT = "%1$-20s%2$-20s%3$-20s%4$-20s%5$-20s%6$s\n";

	public static final String MONTHLY_PAYMENT_FORMAT = "%1$-20d%2$-20.2f%3$-20.2f%4$-20.2f%5$-20.2f%6$-20.2f\n";

	private TextDevice textDevice;

	@Inject TextDeviceMonthyPaymentObserver(TextDevice textDevice) {
		this.textDevice = textDevice;
		textDevice.printf(String.format(HEADING_FORMAT, 	                
				"PaymentNumber", "PaymentAmount", "PaymentInterest","CurrentBalance", "TotalPayments", "TotalInterestPaid"));
	}

	@Override
	public void monthlyAmortizationResult(MonthlyPayment monthly) {
        textDevice.printf(MONTHLY_PAYMENT_FORMAT, monthly.getPaymentNumber(),
                ((double) monthly.getPaymentAmount()) / 100d,
                ((double) monthly.getInterestPaying()) / 100d,
                ((double) monthly.getRemaining()) / 100d,
                ((double) monthly.getTotalPayments()) / 100d,
                ((double) monthly.getTotalInterestPaid()) / 100d);

	}

}
