package listener;

import redis.clients.jedis.Jedis;

public class JedisPublisher implements Runnable {

	private Jedis jedis;
	private String channel;
	private String message;

	public JedisPublisher() {
	}

	public JedisPublisher(Jedis jedis, String channel, String message) {
		this.jedis = jedis;
		this.channel = channel;
		this.message = message;
	}

	@Override
	public void run() {
		jedis.publish(channel, message);
	}
}
