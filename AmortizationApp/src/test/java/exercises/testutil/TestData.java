package exercises.testutil;

import org.testng.annotations.DataProvider;

public class TestData {
	
	/**
	 * List of Example loan input data and expected monthly payment amount, the order of the fields are totalLoanAmount, interest rate, term years, expected monthly payment.
	 * The reason this is kept in a static public method is to for more than one test classes to share the same example data. Both original and refactored code should pass 
	 * tests with the same data set.
	 * 
	 * The sample data is also verified with http://www.amortization-calc.com/
	 * 
	 * @return
	 */
	  @DataProvider
	  public static Object[][] loanExamples() {
	        return new Object[][]{ {100_000, 3.5, 15, 714.88}, //using java 7 syntax, underscore between digits of numbers for readability
	        					   {9_999_999, 5.25, 40, 49_887.03 },
	        					   {10_000_000, 5.375, 40, 50_729.26 }, 
	        					   {5_000, 5.375, 20, 34.04},
	        					   {208_000, 4.25, 30, 1_023.23},
	        					   {2_000, 3.5, 5, 36.38}
	        };
	        
	    }

	
	  
}
