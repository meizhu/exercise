package exercises.refactored.typeconversion;

public interface NumberParser<T> {
	T parse(String s);
}
