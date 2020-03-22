package com.easychat.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.easychat.entity.User;

@Component
public interface UserMapper {

	public List<User> selAll();
	public User selUserByNick(@Param("nick")String nick);
	public User selUserById(@Param("id")String id);
	public void signUp(@Param("id")String id,@Param("nick")String nick,@Param("pwd")String pwd,
			@Param("introduce")String introduce,@Param("SignUpTime")Date SignUpTime);
}
