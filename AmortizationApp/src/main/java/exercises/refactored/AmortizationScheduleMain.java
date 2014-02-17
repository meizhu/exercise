package exercises.refactored;

import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import exercises.refactored.textdevice.TextDevice;

/**
 * This application allows a user enter loan information, then it will print out the amortization schedule table.
 * 
 */
public class AmortizationScheduleMain {
	private static Logger logger = LoggerFactory.getLogger(AmortizationScheduleMain.class);

	public static void main(String[] args) {
		try {
			Injector injector = createInjector();
			ConsoleLoanView consoleView = injector.getInstance(ConsoleLoanView.class);
			Loan loan = consoleView.getLoanInfoFromUser();
			AmortizationCalculator calc = new AmortizationCalculator(loan);
			ScheduleResult result = calc.getSchedule();
			String output = injector.getInstance(AmortizationScheduleFormatter.class).format(loan, result);
			injector.getInstance(TextDevice.class).printf(output);
		} catch (Exception e) {
			logger.warn("Error occured", e);
		}
	}
	
	private static Injector createInjector() {
		return Guice.createInjector(new AbstractModule() {
			@Override protected void configure() {
				bind(AmortizationScheduleFormatter.class).to(BasicAmortizationScheduleFormatter.class);
				bind(TextDevice.class).toInstance(TextDevice.defaultTextDevice());
				bind(Validator.class).toInstance(Validation.buildDefaultValidatorFactory().getValidator());
			}
		});
	}
}
