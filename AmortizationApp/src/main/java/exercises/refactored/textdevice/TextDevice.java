package exercises.refactored.textdevice;

/**
 * Abstraction representing a text input/output device.
 */
public abstract class TextDevice {
	
	public abstract TextDevice printf(String fmt, Object... params);

	public abstract String readLine(String userPrompt);
	
	public static TextDevice defaultTextDevice() {
		if (System.console() != null) {
			return new ConsoleTextDevice(System.console());
		}
		return new StreamTextDevice(System.in, System.out);
	}
	
}