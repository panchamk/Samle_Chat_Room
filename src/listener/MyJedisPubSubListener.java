
/**
* 
*/
package listener;

import redis.clients.jedis.JedisPubSub;

/**
 * @author pancham.gupta
 *
 */
public class MyJedisPubSubListener extends JedisPubSub {
	/**
	* 
	*/
	public MyJedisPubSubListener() {
		super();
	}

	@Override
	public void onMessage(String channel, String message) {
		System.out.println(message);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {

	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("Successfully susbcribed to " + channel);
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
	}

	@Override
	public void onPong(String pattern) {
	}

}