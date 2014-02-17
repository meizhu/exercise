package exercises.refactored;

import javax.inject.Inject;

import exercises.refactored.textdevice.TextDevice;

public class BasicAmortizationScheduleFormatter implements AmortizationScheduleFormatter {


	public String format(Loan loan, ScheduleResult scheduleResult) {
		StringBuilder sb = new StringBuilder();
		String formatString = "%1$-20s%2$-20s%3$-20s%4$-20s%5$-20s%6$s\n";
		sb.append(String.format(formatString, 	                
				"PaymentNumber", "PaymentAmount", "PaymentInterest","CurrentBalance", "TotalPayments", "TotalInterestPaid"));
		
        // output is in dollars
        formatString = "%1$-20d%2$-20.2f%3$-20.2f%4$-20.2f%5$-20.2f%6$-20.2f\n";
        sb.append(String.format(formatString, 0, 0d, 0d,((double) loan.getLoanAmount()),0d, 0d));

        for(MonthlyPayment monthly: scheduleResult.getPayments()) {
	            sb.append(String.format(formatString, monthly.getPaymentNumber(),
	                    ((double) monthly.getPaymentAmount()) / 100d,
	                    ((double) monthly.getInterestPaying()) / 100d,
	                    ((double) monthly.getRemaining()) / 100d,
	                    ((double) monthly.getTotalPayments()) / 100d,
	                    ((double) monthly.getTotalInterestPaid()) / 100d));
	        }
        return sb.toString();
	}

}
