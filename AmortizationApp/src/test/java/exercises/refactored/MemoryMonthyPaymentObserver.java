package exercises.refactored;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory monthly payment observer will save the result in a Collection. This has limitations in terms of how many records it can hold in memory.
 * It should only be used for unit test purpose, or when the number of monthly payments are small.
 * 
 */
public class MemoryMonthyPaymentObserver implements AmortizationCalculator.Observer {

	private List<MonthlyPayment> payments = new ArrayList<>();

	@Override
	public void monthlyComputed(MonthlyPayment monthlyPayment) {
		payments.add(monthlyPayment);
	}

	public List<MonthlyPayment> getPayments() {
		return payments;
	}

}
