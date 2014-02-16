package exercises.refactored;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import exercises.testutil.TestData;

public class AmortizationCalculatorTest {
	
	@Test(dataProvider = "loanExamples", dataProviderClass=TestData.class)
    public void validInput_Produce_Correct_Monthly(double total, double apr, int years, double monthlyPayment){
          Loan loan = new Loan(total, apr, years);
          AmortizationCalculator calculator = new AmortizationCalculator(loan);
          calculator.calculateSchedule();
      	  assertThat("MonthlyAmount", calculator.getMonthlyAmount(), is(Math.round(monthlyPayment*100)));
    }
}
