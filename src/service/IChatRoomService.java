package service;

import java.util.List;

import pojos.ChatMember;
import pojos.ChatMessages;

/**
 * Service for Chat Room
 * 
 * @author pancham
 *
 */
public interface IChatRoomService {

	/**
	 * publishes {@code message} to channel
	 * 
	 * @param channel
	 * @param message
	 */
	void publish(String channel, String message);

	/**
	 * Subscribes to {@code channel}
	 * 
	 * @param channel
	 */
	void subscribe(String channel);

	/**
	 * Subscribes to {@code channel}
	 * 
	 * @param channel
	 */
	void unSubcribe(String channel);

	/**
	 * add the specified user {@code memberName} to the chat room and return
	 * his/her unique chatRoomId
	 * 
	 * @param channel
	 * @param memberName
	 * @return unique memberId
	 */
	String joinChatRoom(String channel, String memberName);

	/**
	 * remove the user from the chat room specified by {@code channel}
	 * 
	 * @param channel
	 * @param member
	 * @return
	 */
	boolean leaveChatRoom(String channel, ChatMember member);

	/**
	 * returns all the message published in the chat room specified by
	 * {@code channel}
	 * 
	 * @param channel
	 */
	List<ChatMessages> getAllMessage(String channel);

	/**
	 * returns all the member subsribed in the chat room specified by
	 * {@code channel}
	 * 
	 * @param channel
	 */
	List<ChatMember> getMembers(String channel);
}
