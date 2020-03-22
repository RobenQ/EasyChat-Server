package com.easychat.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easychat.entity.Package;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SaveIPThread implements Runnable{

	List<HashMap<String,Object>> userInfoList = new ArrayList<HashMap<String,Object>>();

	//判断消息类型并作出相应的判断
	public Map<String, Object> saveIP(Package package1,DatagramPacket datagramPacket,DatagramSocket socket) {
		Map<String, Object> info = new HashMap<String, Object>();
		switch (package1.getType()) {
			case "saveIP":{
				if (userInfoList.size()==0) {
//					System.err.println("===in1");
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("name", package1.getFrom());
					map2.put("ip", datagramPacket.getAddress().toString());
					map2.put("port", datagramPacket.getPort());
					System.out.println(datagramPacket.getAddress()+"==="+datagramPacket.getPort());
					userInfoList.add(map2);
				} else {
					int count = 0;
					for (int i = 0; i < userInfoList.size(); i++) {
						Map<String, Object> map = userInfoList.get(i);
						if (map.get("name").equals(package1.getFrom())) {
//							System.err.println("===in2");
							map.put("name", package1.getFrom());
							map.put("ip", datagramPacket.getAddress().toString());
							map.put("port", datagramPacket.getPort());
							count++;
							System.out.println(datagramPacket.getAddress()+"==="+datagramPacket.getPort());
						}
					}
					if (count==0) {
//						System.err.println("===in3");
						HashMap<String, Object> map2 = new HashMap<String, Object>();
						map2.put("name", package1.getFrom());
						map2.put("ip", datagramPacket.getAddress().toString());
						map2.put("port", datagramPacket.getPort());
						System.out.println(datagramPacket.getAddress()+"==="+datagramPacket.getPort());
						userInfoList.add(map2);
					}
				}
				return null;
			}
			case "getIP":{
				for (int i = 0; i < userInfoList.size(); i++) {
					Map<String, Object> map = userInfoList.get(i);
					if (map.get("name").equals(package1.getFrom())) {
						info.put("ip", map.get("ip"));
						info.put("port", map.get("port"));
						System.out.println(map.get("ip")+"======="+map.get("port"));
						return info;
					}
				}
				break;
			}
			case "msg":{
				for (int i = 0; i < userInfoList.size(); i++) {
					Map<String, Object> map = userInfoList.get(i);
					System.out.println("in======================");
					if (map.get("name").equals(package1.getTo())) {
						String ip = (String) map.get("ip");
						int port = (int) map.get("port");
						ip = ip.substring(1, ip.length());
						System.out.println(ip+"======="+port);
						SocketAddress address = new InetSocketAddress(ip,port);
						Gson gson = new Gson();
						String json = gson.toJson(package1);
						System.err.println(json);
						byte by[] = json.getBytes();
						
	                    DatagramPacket packet = new DatagramPacket(by,by.length,address);
	                    try {
	                    	socket.setSoTimeout(1000*3);
							socket.send(packet);
						} catch (SocketException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				byte[] buf = new byte[1024]; 
				InetSocketAddress address = new InetSocketAddress(2617);
				DatagramSocket socket = new DatagramSocket(address);
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
//				packet.setLength(buf.length);
				String dataString = new String(buf,0,buf.length).trim();
//				System.err.println(dataString);
				Gson gson = new Gson();
				Package Package = gson.fromJson(dataString, Package.class);
				saveIP(Package, packet,socket);
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
