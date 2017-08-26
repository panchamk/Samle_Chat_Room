package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import factory.RedisConnectionFactory;
import util.FileUtils;

public class RedisSetup {
	private static Properties properties;

	/**
	 * @throws IOException
	 * 
	 */
	public static void loadProperties() throws IOException {
		if (properties == null) {
			InputStream inputStream = new FileInputStream(
					new File(FileUtils.getExecutionPath(RedisConnectionFactory.class) + File.separator
							+ "Connection.properties"));
			properties = new Properties();
			properties.load(inputStream);
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public static String getHost() {
		return ((String) properties.get(enums.PropertyKeys.HOST.getPropertyKey()));
	}

	public static int getPort() {
		return Integer.parseInt((String) properties.get(enums.PropertyKeys.PORT.getPropertyKey()));
	}

	public static boolean getSSLEnabled() {
		boolean isSSlEnabled = false;
		String SSL = (String) properties.get(enums.PropertyKeys.SSL.getPropertyKey());
		if (StringUtils.isNotEmpty(SSL)) {
			isSSlEnabled = "true".equalsIgnoreCase(SSL) ? true : false;
		}
		return isSSlEnabled;
	}

	public static String getUserName() {
		return ((String) properties.get(enums.PropertyKeys.USERNAME.getPropertyKey()));
	}

	public static String getPassword() {
		return ((String) properties.get(enums.PropertyKeys.PASSWORD.getPropertyKey()));
	}

}
