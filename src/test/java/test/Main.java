package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {

	List<HashMap<String,String>> userInfoList = new ArrayList<HashMap<String,String>>();
	public static void main(String[] args) {
		try {
			String str_send = "getIP";  
		    byte[] buf = new byte[1024]; 
			InetSocketAddress address = new InetSocketAddress(8080);
			DatagramSocket socket = new DatagramSocket(address);
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			System.out.println("server is on，waiting for client to send data......");
			while (true) {
				socket.receive(packet);
				System.out.println("server received data from client：");
				System.out.println("IP："+packet.getAddress()+"=====端口："+packet.getPort());
				packet.setLength(buf.length);
				String dataString = packet.getData().toString();
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
