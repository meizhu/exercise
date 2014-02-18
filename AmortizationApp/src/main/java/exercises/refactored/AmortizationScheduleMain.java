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
 * This application allows a user to enter loan information, then it will print out the amortization schedule table.
 * 
 */
public class AmortizationScheduleMain {
	private static Logger logger = LoggerFactory.getLogger(AmortizationScheduleMain.class);

	public static void main(String[] args) {
		try {
			Injector injector = createInjector();
			LoanInputHandler inputHandler = injector.getInstance(LoanInputHandler.class);
			Loan loan = inputHandler.getLoanInfo();
			AmortizationCalculator calc = new AmortizationCalculator(loan, injector.getInstance(TextDeviceMonthyPaymentObserver.class));
			calc.calculateSchedule();
		} catch (Exception e) {
			logger.warn("Error occured", e);
		}
	}

	/**
	 * Configure Guice dependency injection container.
	 * 
	 * @return
	 */
	private static Injector createInjector() {
		return Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				//User default system TextDevice
				bind(TextDevice.class).toInstance(TextDevice.defaultTextDevice()); 
				//Hibernate Validator, it will be used to handle input validation
				bind(Validator.class).toInstance(Validation.buildDefaultValidatorFactory().getValidator()); 
				//Oberser to handle monthy payment table result, it prints out result to the output device.
				bind(AmortizationCalculator.Observer.class).to(TextDeviceMonthyPaymentObserver.class); 
			}
		});
	}

}
