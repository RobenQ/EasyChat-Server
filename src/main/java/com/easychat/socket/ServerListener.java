package com.easychat.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread{

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(2617);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("有一个客户端连接了服务器！");
				ChatSocket chatSocket = new ChatSocket(socket);
				chatSocket.start();
//				chatSocket.readMsg("success！");
				ChatManager.getChatManager().add(chatSocket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
