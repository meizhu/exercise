package exercises.refactored.textdevice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

 /**
 * Stream as a text input/output device.
 *
 */
class StreamTextDevice extends TextDevice {

	private final BufferedReader reader;
	private final PrintWriter writer;
	
	public StreamTextDevice(InputStream in, OutputStream out) {
		super();
		reader = new BufferedReader(new InputStreamReader(in));
		writer = new PrintWriter(out, true);
	}

	@Override
	public TextDevice printf(String fmt, Object... params) {
		writer.format(fmt, params);
		return this;
	}

	@Override
	public String readLine(String userPrompt) {
		printf(userPrompt);
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new TextDeviceException(e);
		}
	}

}
