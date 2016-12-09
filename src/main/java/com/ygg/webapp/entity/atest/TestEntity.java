package com.ygg.webapp.entity.atest;

import com.ygg.webapp.entity.base.BaseEntity;

public class TestEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;   //账号
	private String image;//头像
	private String nickname;//昵称
	private Float totalreward;  //历史累计奖励
	private int fans;
	private Float allreward;
	
	
	public int getFans() {
		return fans;
	}
	public void setFans(int fans) {
		this.fans = fans;
	}
	public Float getAllreward() {
		return allreward;
	}
	public void setAllreward(Float allreward) {
		this.allreward = allreward;
	}
	public Float getTotalreward() {
		return totalreward;
	}
	public void setTotalreward(Float totalreward) {
		this.totalreward = totalreward;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
