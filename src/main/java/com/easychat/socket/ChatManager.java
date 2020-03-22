package com.easychat.socket;

import java.util.Vector;

public class ChatManager {
	//单例化
	private ChatManager() {}
	private static final ChatManager chatManager = new ChatManager();
	public static ChatManager getChatManager() {
		return chatManager;
	}
	
//	List<ChatSocket> chatSockets = new ArrayList<ChatSocket>();
	Vector<ChatSocket> chatSockets = new Vector<ChatSocket>();
	
	public void add(ChatSocket chatSocket) {
		chatSockets.add(chatSocket);
	}
	
	public void publish(ChatSocket chatSocket,String msg) {
		for (int i = 0; i < chatSockets.size(); i++) {
			ChatSocket chatSocket2 = chatSockets.get(i);
			if ((!chatSocket.equals(chatSocket2))) {
				chatSocket2.readMsg(msg);
			}
		}
//		for (int i = 0; i < chatSockets.size(); i++) {
//			ChatSocket chatSocket2 = chatSockets.get(i);
//			if (!(chatSocket2.getSocket().isConnected())) {
//				chatSockets.remove(i);
//			}
//		}
	}
	
}
