package service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import factory.RedisConnectionFactory;
import listener.JedisPublisher;
import listener.JedisSubscriber;
import logger.FileLogger;
import pojos.ChatMember;
import pojos.ChatMessages;
import redis.clients.jedis.Jedis;
import service.IChatRoomService;

public class ChatRoomService implements IChatRoomService {

	private FileLogger logger = FileLogger.getInstance();
	private static ChatRoomService chatRoomService;

	public static IChatRoomService getInstance() {
		if (chatRoomService == null)
			chatRoomService = new ChatRoomService();
		return chatRoomService;
	}

	@Override
	public void publish(String channel, String message) {
		Jedis jedis = null;
		try {
			jedis = RedisConnectionFactory.getRedisConnection();
			JedisPublisher jedisPublisher = new JedisPublisher(jedis, channel, message);
			ExecutorService executorService = Executors.newCachedThreadPool();
			executorService.submit(jedisPublisher);
		} catch (Exception e) {
			logger.writeLog(String.format("error while publishing message to channel : %s ", channel), e);
			throw new RuntimeException(e);
		} finally {
			if (jedis != null)
				RedisConnectionFactory.releaseJedisConnection(jedis);
		}

	}

	@Override
	public void subscribe(String channel) {
		Jedis jedis = null;
		try {
			jedis = RedisConnectionFactory.getRedisConnection();
			JedisSubscriber jedisSubscriber = new JedisSubscriber(jedis, channel);
			ExecutorService executorService = Executors.newCachedThreadPool();
			executorService.submit(jedisSubscriber);
		} catch (Exception e) {
			logger.writeLog(String.format("error while subscribing to channel : %s ", channel), e);
			throw new RuntimeException(e);
		} finally {
			if (jedis != null)
				RedisConnectionFactory.releaseJedisConnection(jedis);
		}

	}

	@Override
	public void unSubcribe(String channel) {
		// TODO Auto-generated method stub

	}

	@Override
	public String joinChatRoom(String channel, String memberName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean leaveChatRoom(String channel, ChatMember member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ChatMessages> getAllMessage(String channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMember> getMembers(String channel) {
		// TODO Auto-generated method stub
		return null;
	}

}
