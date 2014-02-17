package exercises.refactored.typeconversion;

public class IntegerParser implements NumberParser<Integer> {

	@Override
	public Integer parse(String s) {
		return Integer.parseInt(s);
	}
	
}
