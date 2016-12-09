package com.ygg.webapp.entity;

import java.sql.Timestamp;

public class NewLogisticsMessageEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String channel;
	private String number;
	private String logg;
	private int isinside;
	private Timestamp operatetime;
	private String content;
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getLogg() {
		return logg;
	}
	public void setLogg(String logg) {
		this.logg = logg;
	}
	public int getIsinside() {
		return isinside;
	}
	public void setIsinside(int isinside) {
		this.isinside = isinside;
	}
	public Timestamp getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Timestamp operatetime) {
		this.operatetime = operatetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "NewLogisticsMessageEntity [channel=" + channel + ", number=" + number + ", logg=" + logg + ", isinside="
				+ isinside + ", operatetime=" + operatetime + ", content=" + content + "]";
	}	
}
