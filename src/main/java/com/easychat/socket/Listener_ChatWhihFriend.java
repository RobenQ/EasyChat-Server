package com.easychat.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Listener_ChatWhihFriend extends Thread {

	
	@Override
	public void run() {
		try {
			String str_send = "getIP";  
		    byte[] buf = new byte[1024]; 
			InetSocketAddress address = new InetSocketAddress("127.0.0.1", 2510);
			DatagramSocket socket = new DatagramSocket(address);
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			while (true) {
				socket.connect(address);
				socket.receive(packet);
				System.out.println("IP："+packet.getAddress()+"=====端口："+packet.getPort());
				packet.setLength(buf.length);
				socket.close();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
