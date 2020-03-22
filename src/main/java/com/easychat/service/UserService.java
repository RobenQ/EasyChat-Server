package com.easychat.service;

import java.util.List;

import com.easychat.entity.User;

public interface UserService {

	public List<User> selAll();
	public User selUserByNick(String nick);
	public User selUserById(String id);
	public void signUp(User user);
	public List<User> getfriendList(String id);
}
