package com.easychat.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easychat.entity.Friends;
import com.easychat.entity.User;
import com.easychat.mapper.FriendsMapper;
import com.easychat.service.FriendsService;

@Service
public class FriendsServiceImpl implements FriendsService{

	@Autowired
	private FriendsMapper friendsMapper;
	
	@Override
	public List<Friends> selFriend(String id) {
		return friendsMapper.selFriend(id);
	}

}
