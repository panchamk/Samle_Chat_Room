
import java.util.Scanner;

import factory.ChatRoomServiceFactory;
import factory.RedisConnectionFactory;
import logger.FileLogger;
import redis.clients.jedis.Jedis;
import service.IChatRoomService;

/**
* 
*/

/**
 * @author pancham.gupta
 *
 */
public class TestMain {

	private final static String CHANNEL = "CHAT_ROOM";
	private final static String PUBLISH = "PUBLISH";
	private final static String SUBSCRIBE = "SUBSCRIBE";
	private final static String CANCEL = "CANCEL";
	private static Jedis jedis;

	private static final String LINE_SEPARATOR_KEY = "line.separator";
	private static FileLogger fileLogger = FileLogger.getInstance();

	public static void main(String[] args) {
		try {
			setup();
			IChatRoomService chatRoomService = ChatRoomServiceFactory.getInstance();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter SUBSCRIBE to subscribe to the chat room");
			System.out.println("Enter PUBLISH <space> MESSAGE to the chat room");
			System.out.println("Enter CANCEL to leave the chat room");
			while (true) {
				String readIn = scanner.nextLine();
				if (SUBSCRIBE.equalsIgnoreCase(readIn)) {
					chatRoomService.subscribe(CHANNEL);
				} else if (readIn.toUpperCase().contains(PUBLISH)) {
					String[] inputs = readIn.split("\\s+");
					// if (inputs[1].equalsIgnoreCase(CHANNEL)) {
					// jedis.publish(CHANNEL, inputs[2]);
					// }
					// jedis.publish(CHANNEL, inputs[1]);
					chatRoomService.publish(CHANNEL, inputs[1]);
				} else if (readIn.equalsIgnoreCase(CANCEL))
					break;
				else
					System.out.println("Invalid Input. Please Try again");
			}

		} catch (Exception e) {
			fileLogger.writeLog("asd", e);
		} finally {
			RedisConnectionFactory.releaseJedisConnection(jedis);
			RedisConnectionFactory.releasePool();
		}

	}

	/**
	 * @throws Exception
	 * 
	 */
	private static void setup() throws Exception {
		jedis = RedisConnectionFactory.getRedisConnection();
		// subscribeToChannel();

	}

	/**
	* 
	*/
	private static void subscribeToChannel() {
		try {
			jedis.subscribe(RedisConnectionFactory.getJedisPubSub(), CHANNEL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
