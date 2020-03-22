package com.easychat.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread{
	private Socket socket;
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ChatSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void readMsg(String msg){
		try {
			System.out.println(msg);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write((msg+"\n").getBytes("UTF-8"));
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				ChatManager.getChatManager().publish(this, line);
			}
//			bufferedReader.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
