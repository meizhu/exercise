package exercises.refactored;

import javax.inject.Singleton;

import net.jcip.annotations.NotThreadSafe;

import org.springframework.util.Assert;

/**
 * This is the core AmortizationCalculator.
 * The usage should be:
 * <ol>
 * <li>
 * Create a new instance of <code>AmortizationCalculator</code>, with loan info passed in. 
 * </li>
 * <li>
 * Call <code>getSchedule()</code> to get the calculation result.
 * </li>
 * 
 * </ol>
 */
@NotThreadSafe
@Singleton
public class AmortizationCalculator {
	
	public interface Observer {
		void monthlyAmortizationResult(MonthlyPayment monthlyPayment);
	}
	
	Observer observer;
	
	private static final int DOLLAR_TO_CENTS_FACTOR = 100;
	private static final int MONTHS_PAY_YEAR = 12;
	private final Loan loan;
	private ScheduleResult schedule = new ScheduleResult();
	private long monthlyAmount; // in cents
	private long amountBorrowed; // in cents
	private int termMonths = 0;
	private double monthlyInterest;
	private int maxNumberOfPayments;
	private boolean calculationDone;
	/**
	 * Temorary calculating fields
	 */
	private long balance=0;
	private int paymentNumber = 0;
	private long totalPayments = 0;
	private long totalInterestPaid = 0;
	private long curMonthlyInterest;
	private long curPayoffAmount;
	private long curMonthlyPaymentAmount; 
	private long curMonthlyPrincipalPaid;
	private long curBalance; 
	
	public AmortizationCalculator(Loan loanInput, Observer observer) {
		Assert.notNull(loanInput, "Loan info must not be null");
		this.loan = loanInput;
		this.observer = observer;
	}

	public void calculateSchedule() {
		initFieldsFromLoanInfo();
		calculateMonthlyAmount();
		calculateAmortizationTable();
		summarizeSchedule();
		calculationDone = true;
	}
	
	private void doCalculationIfNotDone() {
		if(!calculationDone) {
			calculateSchedule();
		}
	}
	private void summarizeSchedule() {
//		schedule.setPayments(payments);
		schedule.setMonthlyAmount(monthlyAmount);
		schedule.setNumberOfPayments(paymentNumber);
		schedule.setTotalInterestPaid(totalInterestPaid);
		schedule.setTotalPayments(totalPayments);
		
	}

	private void initFieldsFromLoanInfo() {
		termMonths = loan.getTermYears() * MONTHS_PAY_YEAR;
		maxNumberOfPayments = termMonths + 1;
		amountBorrowed = Math.round(loan.getLoanAmount() * DOLLAR_TO_CENTS_FACTOR); // in cents
		monthlyInterest = loan.getInterestRate() / (MONTHS_PAY_YEAR * DOLLAR_TO_CENTS_FACTOR);
		balance = amountBorrowed;
	}
	
	private void calculateMonthlyAmount() {
		// this is 1 / (1 + J)
		double tmp = Math.pow(1d + monthlyInterest, -1);

		// this is Math.pow(1/(1 + J), N)
		tmp = Math.pow(tmp, termMonths);

		// this is 1 / (1 - (Math.pow(1/(1 + J), N))))
		tmp = Math.pow(1d - tmp, -1);

		// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
		double rc = amountBorrowed * monthlyInterest * tmp;
		monthlyAmount = Math.round(rc);
	}

	private void calculateAmortizationTable() {
		while ((balance > 0) && (paymentNumber <= maxNumberOfPayments)) {
			//payments.add(computeCurMonthlyPayment());
			observer.monthlyAmortizationResult(computeCurMonthlyPayment());
		}
	}

	private boolean shouldPayoffRemaining() {
		return ((paymentNumber == maxNumberOfPayments) && ((curMonthlyPaymentAmount == 0) || (curMonthlyPaymentAmount == curMonthlyInterest)));
	}
	
	private MonthlyPayment computeCurMonthlyPayment() {
		// Calculate H = P x J, this is your current monthly interest
		curMonthlyInterest = Math.round(((double) balance) * monthlyInterest);

		// the amount required to payoff the loan
		curPayoffAmount = balance + curMonthlyInterest;

		// the amount to payoff the remaining balance may be less than the calculated monthlyPaymentAmount
		curMonthlyPaymentAmount = Math.min(monthlyAmount, curPayoffAmount);

		// it's possible that the calculated monthlyPaymentAmount is 0,
		// or the monthly payment only covers the interest payment - i.e. no principal
		// so the last payment needs to payoff the loan
		if (shouldPayoffRemaining()) {
			curMonthlyPaymentAmount = curPayoffAmount;
		}

		// Calculate C = M - H, this is your monthly payment minus your monthly interest,
		// so it is the amount of principal you pay for that month
		curMonthlyPrincipalPaid = curMonthlyPaymentAmount - curMonthlyInterest;

		// Calculate Q = P - C, this is the new balance of your principal of your loan.
		curBalance = balance - curMonthlyPrincipalPaid;
		// Set P equal to Q and go back to Step 1: You thusly loop around until the value Q (and hence P) goes to zero.
		balance = curBalance;
		totalPayments += curMonthlyPaymentAmount;
        totalInterestPaid += curMonthlyInterest;
        ++paymentNumber;
		return createMonthlyPayment();
		
	}

	private MonthlyPayment createMonthlyPayment() {
		MonthlyPayment eachPayment = new MonthlyPayment();
		eachPayment.setPaymentNumber(paymentNumber);
		eachPayment.setPaymentAmount(curMonthlyPaymentAmount);
		eachPayment.setInterestPaying(curMonthlyInterest);
		eachPayment.setPrincipalPaying(curMonthlyPrincipalPaid);
		eachPayment.setTotalInterestPaid(totalInterestPaid);
		eachPayment.setTotalPayments(totalPayments);
		eachPayment.setRemaining(curBalance);
		return eachPayment;
	}



	public long getMonthlyAmount() {
		doCalculationIfNotDone();
		return this.monthlyAmount;
	}
	
	public ScheduleResult getSchedule() {
		doCalculationIfNotDone();
		return this.schedule;
	}

}
