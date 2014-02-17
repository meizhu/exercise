package exercises.refactored;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import exercises.refactored.textdevice.TextDevice;
import exercises.testutil.TestData;

public class AmortizationCalculatorTest {

//	private Injector injector = createInjector();

	@Test(dataProvider = "loanExamples", dataProviderClass=TestData.class)
    public void validInput_Produce_Correct_Monthly(double total, double apr, int years, double monthlyPayment){
          Loan loan = new Loan(total, apr, years);
          MemoryMonthyPaymentObserver observer = new  MemoryMonthyPaymentObserver();
          AmortizationCalculator calculator = new AmortizationCalculator(loan, observer);
          calculator.calculateSchedule();
      	  assertThat("MonthlyAmount", calculator.getMonthlyAmount(), is(Math.round(monthlyPayment*100)));
    }

	@Test
    public void testMontlyDetails() {
        Loan loan = new Loan(2000D, 3.5D, 5);
        MemoryMonthyPaymentObserver observer = new  MemoryMonthyPaymentObserver();
        AmortizationCalculator calculator = new AmortizationCalculator(loan, observer);
        calculator.calculateSchedule();
        List<MonthlyPayment> payments = observer.getPayments();
        assertThat("month count", payments.size(), is(MONTHLY_RESULTS.length));
        for (int i = 0; i < payments.size(); ++i) {
        	MonthlyPayment payment = payments.get(i);
        	assertThat("payment amount", payment.getPaymentAmount()/100D, is(MONTHLY_RESULTS[i][1]));
        }
	}
	
	  /**
	   * The detailed results for a $2000 load at 3.5% over 5 years.
	   * The columns are:  PaymentNumber, PaymentAmount, PaymentInterest, CurrentBalance, TotalPayments, TotalInterestPaid
	   * @return the detailed results
	   */
	  public static double[][]  MONTHLY_RESULTS = new double[][] {
				  {       1,    36.38,     5.83,  1969.45,    36.38,     5.83 },
				  {       2,    36.38,     5.74,  1938.81,    72.76,    11.57 },
				  {       3,    36.38,     5.65,  1908.08,   109.14,    17.22 },
				  {       4,    36.38,     5.57,  1877.27,   145.52,    22.79 },
				  {       5,    36.38,     5.48,  1846.37,   181.90,    28.27 },
				  {       6,    36.38,     5.39,  1815.38,   218.28,    33.66 },
				  {       7,    36.38,     5.29,  1784.29,   254.66,    38.95 },
				  {       8,    36.38,     5.20,  1753.11,   291.04,    44.15 },
				  {       9,    36.38,     5.11,  1721.84,   327.42,    49.26 },
				  {      10,    36.38,     5.02,  1690.48,   363.80,    54.28 },
				  {      11,    36.38,     4.93,  1659.03,   400.18,    59.21 },
				  {      12,    36.38,     4.84,  1627.49,   436.56,    64.05 },
				  {      13,    36.38,     4.75,  1595.86,   472.94,    68.80 },
				  {      14,    36.38,     4.65,  1564.13,   509.32,    73.45 },
				  {      15,    36.38,     4.56,  1532.31,   545.70,    78.01 },
				  {      16,    36.38,     4.47,  1500.40,   582.08,    82.48 },
				  {      17,    36.38,     4.38,  1468.40,   618.46,    86.86 },
				  {      18,    36.38,     4.28,  1436.30,   654.84,    91.14 },
				  {      19,    36.38,     4.19,  1404.11,   691.22,    95.33 },
				  {      20,    36.38,     4.10,  1371.83,   727.60,    99.43 },
				  {      21,    36.38,     4.00,  1339.45,   763.98,   103.43 },
				  {      22,    36.38,     3.91,  1306.98,   800.36,   107.34 },
				  {      23,    36.38,     3.81,  1274.41,   836.74,   111.15 },
				  {      24,    36.38,     3.72,  1241.75,   873.12,   114.87 },
				  {      25,    36.38,     3.62,  1208.99,   909.50,   118.49 },
				  {      26,    36.38,     3.53,  1176.14,   945.88,   122.02 },
				  {      27,    36.38,     3.43,  1143.19,   982.26,   125.45 },
				  {      28,    36.38,     3.33,  1110.14,  1018.64,   128.78 },
				  {      29,    36.38,     3.24,  1077.00,  1055.02,   132.02 },
				  {      30,    36.38,     3.14,  1043.76,  1091.40,   135.16 },
				  {      31,    36.38,     3.04,  1010.42,  1127.78,   138.20 },
				  {      32,    36.38,     2.95,   976.99,  1164.16,   141.15 },
				  {      33,    36.38,     2.85,   943.46,  1200.54,   144.00 },
				  {      34,    36.38,     2.75,   909.83,  1236.92,   146.75 },
				  {      35,    36.38,     2.65,   876.10,  1273.30,   149.40 },
				  {      36,    36.38,     2.56,   842.28,  1309.68,   151.96 },
				  {      37,    36.38,     2.46,   808.36,  1346.06,   154.42 },
				  {      38,    36.38,     2.36,   774.34,  1382.44,   156.78 },
				  {      39,    36.38,     2.26,   740.22,  1418.82,   159.04 },
				  {      40,    36.38,     2.16,   706.00,  1455.20,   161.20 },
				  {      41,    36.38,     2.06,   671.68,  1491.58,   163.26 },
				  {      42,    36.38,     1.96,   637.26,  1527.96,   165.22 },
				  {      43,    36.38,     1.86,   602.74,  1564.34,   167.08 },
				  {      44,    36.38,     1.76,   568.12,  1600.72,   168.84 },
				  {      45,    36.38,     1.66,   533.40,  1637.10,   170.50 },
				  {      46,    36.38,     1.56,   498.58,  1673.48,   172.06 },
				  {      47,    36.38,     1.45,   463.65,  1709.86,   173.51 },
				  {      48,    36.38,     1.35,   428.62,  1746.24,   174.86 },
				  {      49,    36.38,     1.25,   393.49,  1782.62,   176.11 },
				  {      50,    36.38,     1.15,   358.26,  1819.00,   177.26 },
				  {      51,    36.38,     1.04,   322.92,  1855.38,   178.30 },
				  {      52,    36.38,     0.94,   287.48,  1891.76,   179.24 },
				  {      53,    36.38,     0.84,   251.94,  1928.14,   180.08 },
				  {      54,    36.38,     0.73,   216.29,  1964.52,   180.81 },
				  {      55,    36.38,     0.63,   180.54,  2000.90,   181.44 },
				  {      56,    36.38,     0.53,   144.69,  2037.28,   181.97 },
				  {      57,    36.38,     0.42,   108.73,  2073.66,   182.39 },
				  {      58,    36.38,     0.32,    72.67,  2110.04,   182.71 },
				  {      59,    36.38,     0.21,    36.50,  2146.42,   182.92 },
				  {      60,    36.38,     0.11,     0.23,  2182.80,   183.03 },
				  {      61,     0.23,     0.00,     0.00,  2183.03,   183.03 },
			  };
	  
	
}
