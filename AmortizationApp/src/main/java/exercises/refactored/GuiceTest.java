package exercises.refactored;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import exercises.refactored.textdevice.TextDevice;

public class GuiceTest {

	interface IA {

	}

	public static class A implements IA {
		
		@Inject
		private TextDevice textDevice;
		
		public void foo() {
			textDevice.printf("foo\n");
		}
	}

	public static class B {

		@Inject
		private A a;

		public void bar() {
			a.foo();
		}

	}

	public static class AsdfModule extends AbstractModule {
		@Override
		protected void configure() {
			bind(IA.class).to(A.class);
			bind(TextDevice.class).toInstance(TextDevice.defaultTextDevice());
			bind(Validator.class).toInstance(Validation.buildDefaultValidatorFactory().getValidator());
		}
	}

	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AsdfModule());
		B b = injector.getInstance(B.class);
		b.bar();
		
		  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
          Validator validator = factory.getValidator();
          Loan loan = new Loan(0.001D, 3.5D, 5);
          Set<ConstraintViolation<Loan>> constraintViolations = validator.validateProperty(loan, "loanAmount");
          System.out.println(constraintViolations.size());
          for (ConstraintViolation<Loan> x : constraintViolations) {
        	  System.out.println(x.getPropertyPath() + ": " + x.getMessage());
          }
	}

}
