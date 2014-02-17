package exercises.refactored;

import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

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
			Loan loan = consoleView.getLoanInfo();
			AmortizationCalculator calc = new AmortizationCalculator(loan, injector.getInstance(TextDeviceMonthyPaymentObserver.class));
			calc.getSchedule();
		} catch (Exception e) {
			logger.warn("Error occured", e);
		}
	}

	private static Injector createInjector() {
		return Guice.createInjector(createModule());
	}
	
	static Module createModule() {
		return new AbstractModule() {
			@Override protected void configure() {
				bind(TextDevice.class).toInstance(TextDevice.defaultTextDevice());
				bind(Validator.class).toInstance(Validation.buildDefaultValidatorFactory().getValidator());
				bind(AmortizationCalculator.Observer.class).to(TextDeviceMonthyPaymentObserver.class);
			}
		};
	}
	
}
