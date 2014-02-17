package exercises.refactored;

import java.util.ArrayList;
import java.util.List;

public class MemoryMonthyPaymentObserver implements AmortizationCalculator.Observer {

	private List<MonthlyPayment> payments = new ArrayList<>();

	@Override
	public void monthlyAmortizationResult(MonthlyPayment monthlyPayment) {
		payments.add(monthlyPayment);
	}

	public List<MonthlyPayment> getPayments() {
		return payments;
	}

}
