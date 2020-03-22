package com.easychat.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easychat.entity.Friends;
import com.easychat.entity.User;
import com.easychat.mapper.FriendsMapper;
import com.easychat.mapper.UserMapper;
import com.easychat.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private FriendsMapper friendsMapper;
	
	@Override
	public List<User> selAll() {
		return userMapper.selAll();
	}
	@Override
	public User selUserByNick(String nick) {
		return userMapper.selUserByNick(nick);
	}
	@Override
	public void signUp(User user) {
		userMapper.signUp(user.getId(), user.getNick(), user.getPassword(), user.getIntroduce(), user.getSignUpTime());
	}
	@Override
	public User selUserById(String id) {
		return userMapper.selUserById(id);
	}
	@Override
	public List<User> getfriendList(String id) {
		List<Friends> friends = friendsMapper.selFriend(id);
		List<User> users = new ArrayList<User>();
		for (Friends friends2 : friends) {
			if (friends2.getUserid_1().equalsIgnoreCase(id)) {
				users.add(userMapper.selUserById(friends2.getUserid_2()));
			} else {
				users.add(userMapper.selUserById(friends2.getUserid_1()));
			}
		}
		return users;
	}

}
