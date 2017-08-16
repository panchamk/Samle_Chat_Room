
/**
* 
*/
package factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import logger.FileLogger;
import pojo.MyJedisPubSubListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import util.FileUtils;

/**
 * @author pancham.gupta
 *
 */
public abstract class RedisConnectionFactory {

	private static FileLogger fileLogger = FileLogger.getInstance();
	private static Properties properties;

	public static Jedis getRedisConnection() throws Exception {
		Jedis jedis = null;
		try {
			loadProperties();
			jedis = new Jedis(getHost(), getPort());
		} catch (IOException e) {
			fileLogger.writeLog("asd", e);
		}
		return jedis;
	}

	public static JedisPubSub getJedisPubSub() throws Exception {
		JedisPubSub jedisPubSub = null;
		try {
			loadProperties();
			jedisPubSub = new MyJedisPubSubListener();
		} catch (IOException e) {
			fileLogger.writeLog("asd", e);
		}
		return jedisPubSub;

	}

	/**
	 * @throws IOException
	 * 
	 */
	private static void loadProperties() throws IOException {
		if (properties == null) {

			/*
			 * InputStream inputStream =
			 * Thread.currentThread().getContextClassLoader().
			 * getResourceAsStream( "Connection.properties");
			 */
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
}