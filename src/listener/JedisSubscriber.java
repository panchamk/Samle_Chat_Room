package listener;

import factory.RedisConnectionFactory;
import logger.FileLogger;
import redis.clients.jedis.Jedis;

public class JedisSubscriber implements Runnable {
	private Jedis jedis;
	private String channel;
	private static FileLogger fileLogger = FileLogger.getInstance();

	public JedisSubscriber() {
	}

	public JedisSubscriber(Jedis jedis, String channel) {
		this.jedis = jedis;
		this.channel = channel;
	}

	@Override
	public void run() {
		try {
			jedis.subscribe(RedisConnectionFactory.getJedisPubSub(), channel);
		} catch (Exception e) {
			fileLogger.writeLog("ldlsd", e);
		}
	}
}
