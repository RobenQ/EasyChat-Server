package com.easychat.entity;

import java.sql.Date;

public class Friends {

	private String userid_1;
	private String userid_2;
	private Date friendTime;
	public Friends() {
		super();
	}
	public Friends(String userid_1, String userid_2, Date friendTime) {
		super();
		this.userid_1 = userid_1;
		this.userid_2 = userid_2;
		this.friendTime = friendTime;
	}
	public String getUserid_1() {
		return userid_1;
	}
	public void setUserid_1(String userid_1) {
		this.userid_1 = userid_1;
	}
	public String getUserid_2() {
		return userid_2;
	}
	public void setUserid_2(String userid_2) {
		this.userid_2 = userid_2;
	}
	public Date getFriendTime() {
		return friendTime;
	}
	public void setFriendTime(Date friendTime) {
		this.friendTime = friendTime;
	}
	@Override
	public String toString() {
		return "Friends [userid_1=" + userid_1 + ", userid_2=" + userid_2 + ", friendTime=" + friendTime + "]";
	}
}
