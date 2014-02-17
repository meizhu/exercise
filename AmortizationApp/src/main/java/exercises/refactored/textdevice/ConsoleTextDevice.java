package exercises.refactored.textdevice;

import java.io.Console;

public class ConsoleTextDevice extends TextDevice {

	private final Console console;

	public ConsoleTextDevice(Console console) {
		super();
		this.console = console;
	}

	@Override
	public TextDevice printf(String fmt, Object... params) {
		console.format(fmt, params);
		return this;
	}

	@Override
	public String readLine(String userPrompt) {
		return console.readLine(userPrompt);
	}

	
}
