package exercises.original;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.Test;

import exercises.testutil.TestData;


/**
 * User: mzhu
 * Date: 2/13/14 10:25 PM
 */
public class AmortizationScheduleTest {
    @Test(dataProvider = "loanExamples", dataProviderClass=TestData.class)
    public void validInput_Produce_Correct_Monthly(double total, double apr, int years, double monthlyPayment){
          AmortizationSchedule amortization = new AmortizationSchedule(total, apr, years);
          long monthly = (Long)ReflectionTestUtils.getField(amortization, "monthlyPaymentAmount");
     	  assertThat("MonthlyAmount", monthly, is(Math.round(monthlyPayment*100)));
     	  
    }


}
