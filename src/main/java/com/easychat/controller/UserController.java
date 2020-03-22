package com.easychat.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.easychat.Utils.MyUtils;
import com.easychat.entity.Friends;
import com.easychat.entity.User;
import com.easychat.mapper.FriendsMapper;
import com.easychat.service.FriendsService;
import com.easychat.service.UserService;
import com.google.gson.Gson;

@Controller
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendsService friendsService;
	
	@RequestMapping("test")
	public Map<String, Object> test() {
		System.out.println("=================test");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "成功！");
		return map;
	}
	
	//登录控制
	@RequestMapping(value="login",method=RequestMethod.POST)
	public Map<String, Object> login(@RequestParam String nick,@RequestParam String pwd){
		Map<String, Object> map = new HashMap<String, Object>();
		if (nick!=null && pwd!=null) {
			User user = userService.selUserByNick(nick);
			if (user!=null && user.getPassword().equals(pwd)) {
				map.put("msg", 1);
				map.put("nick", user.getNick());
				map.put("id", user.getId());
				map.put("introduce", user.getIntroduce());
			}else {
				map.put("msg", 0);
			}
		}else {
			map.put("msg", 0);
		}
		
		return map;
	}
	
	//用户注册控制
	@RequestMapping("signup")
	public Map<String, Object> signup(@RequestParam String nick,@RequestParam String pwd) throws NoSuchAlgorithmException {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.selUserByNick(nick);
		if (user==null) {
			String size = String.valueOf(userService.selAll().size()+1);
			String introduce = "主人很懒，什么都没有写！";
			user = new User(size, nick, MyUtils.sHAEncrypt(pwd), introduce, new Date(System.currentTimeMillis()));
			userService.signUp(user);
			map.put("msg", 1);
		}else {
			map.put("msg", 0);
		}
		return map;
	}
	
	//获取好友列表
	@RequestMapping("getfriend")
	public Map<String, Object> getFriend(@RequestParam String id) throws NoSuchAlgorithmException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (id.equalsIgnoreCase("error")) {
			map.put("msg", "error");
		} else {
			List<User> users = userService.getfriendList(id);
			for (User user : users) {
				user.setPassword(" ");
			}
			System.err.println(id);
			Gson gson = new Gson();
			String json = gson.toJson(users);
			System.err.println(json);
			map.put("friends", json);
			map.put("msg", "ok");
		}
		return map;
	}
}
