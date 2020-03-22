package com.easychat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.easychat.socket.SaveIPThread;
import com.easychat.socket.ServerListener;

@SpringBootApplication
//@ServletComponentScan			//java原生servlet注解扫描
@MapperScan("com.easychat.mapper")
public class Start {
	public static void main(String[] args) {
//		new ServerListener().start();
		new Thread(new SaveIPThread()).start();
		SpringApplication.run(Start.class, args);
	}
}
