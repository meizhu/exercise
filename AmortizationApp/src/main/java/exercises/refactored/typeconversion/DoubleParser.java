package exercises.refactored.typeconversion;


public class DoubleParser implements NumberParser<Double> {

	@Override
	public Double parse(String s) {
		return Double.parseDouble(s);
	}

	
}
