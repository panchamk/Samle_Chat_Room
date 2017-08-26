package factory;

import service.IChatRoomService;
import service.impl.ChatRoomService;

public class ChatRoomServiceFactory {

	public static IChatRoomService getInstance() {
		return ChatRoomService.getInstance();
	}
}
