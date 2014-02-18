package exercises.refactored.textdevice;

/**
 * Abstraction representing a text input/output device.
 */
public abstract class TextDevice {
	
	public abstract TextDevice printf(String fmt, Object... params);

	public abstract String readLine(String userPrompt);
	
	/**
	 * Returns the optimal input/outpu device from the runtime environment
	 * 
	 * @return
	 */
	public static TextDevice defaultTextDevice() {
		if (System.console() != null) {
			return new ConsoleTextDevice(System.console());
		}
		return new StreamTextDevice(System.in, System.out);
	}
	
}