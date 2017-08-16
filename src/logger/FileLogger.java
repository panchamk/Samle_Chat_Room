
/**
* 
*/
package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import util.FileUtils;

/**
 * @author pancham.gupta
 *
 */
public class FileLogger {

	private static FileLogger logger;
	private File javaMigrationLogFile;

	private static final String LINE_SEPARATOR_KEY = "line.separator";

	/**
	* 
	*/
	private FileLogger() {
		try {
			setup();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static FileLogger getInstance() {
		if (logger == null)
			return new FileLogger();
		return logger;
	}

	public void setup() throws IOException {
		File javaMigrationLogFile = new File(
				FileUtils.getExecutionPath(FileLogger.class) + File.separator + "errorLog.log");
		if (!javaMigrationLogFile.exists()) {
			javaMigrationLogFile.createNewFile();
		}
	}

	private FileWriter getWriter() throws IOException {
		return new FileWriter(javaMigrationLogFile, true);
	}

	/**
	 * @param e
	 */
	public void writeLog(String message, Exception ex) {
		FileWriter fileWriter;
		try {
			fileWriter = getWriter();
			fileWriter.write(System.getProperty(LINE_SEPARATOR_KEY) + message);
			fileWriter.write(System.getProperty(LINE_SEPARATOR_KEY) + ex.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}