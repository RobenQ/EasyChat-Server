package com.easychat.socket;

import java.net.DatagramSocket;

public class Socket_ChatWhitFriend {
	private String user_from;
	private String user_to;
	private DatagramSocket socket;
	public String getUser_from() {
		return user_from;
	}
	public void setUser_from(String user_from) {
		this.user_from = user_from;
	}
	public String getUser_to() {
		return user_to;
	}
	public void setUser_to(String user_to) {
		this.user_to = user_to;
	}
	public DatagramSocket getSocket() {
		return socket;
	}
	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	public Socket_ChatWhitFriend(String user_from, String user_to, DatagramSocket socket) {
		super();
		this.user_from = user_from;
		this.user_to = user_to;
		this.socket = socket;
	}
	
}
