package com.easychat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easychat.entity.Friends;
import com.easychat.entity.User;

public interface FriendsMapper {

	public List<Friends> selFriend(@Param("userid")String userid);
}
