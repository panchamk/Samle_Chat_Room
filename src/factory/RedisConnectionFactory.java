
/**
* 
*/
package factory;

import java.io.IOException;

import helper.RedisSetup;
import listener.MyJedisPubSubListener;
import logger.FileLogger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @author pancham.gupta
 *
 */
public abstract class RedisConnectionFactory {

	private static FileLogger fileLogger = FileLogger.getInstance();
	private static JedisPool jedisPool;

	public static Jedis getRedisConnection() throws Exception {
		Jedis jedis = null;
		try {
			RedisSetup.loadProperties();
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			jedisPool = new JedisPool(poolConfig, RedisSetup.getHost(), RedisSetup.getPort(), Protocol.DEFAULT_TIMEOUT);
			jedis = jedisPool.getResource();
			if (RedisSetup.getSSLEnabled())
				jedis.auth(RedisSetup.getPassword());
		} catch (IOException e) {
			fileLogger.writeLog("asd", e);
		}
		return jedis;
	}

	public static void releaseJedisConnection(Jedis jedis) {
		try {
			jedis.close();
			jedis = null;
		} catch (JedisException ex) {
			fileLogger.writeLog("Error in closing connection", ex);
		}

	}

	public static void releasePool() {
		try {
			jedisPool.destroy();
		} catch (JedisException ex) {
			fileLogger.writeLog("Error in destroying jedis connection pool", ex);
		}
	}

	public static JedisPubSub getJedisPubSub() throws Exception {
		JedisPubSub jedisPubSub = null;
		try {
			RedisSetup.loadProperties();
			jedisPubSub = new MyJedisPubSubListener();
		} catch (IOException e) {
			fileLogger.writeLog("asd", e);
		}
		return jedisPubSub;

	}
}