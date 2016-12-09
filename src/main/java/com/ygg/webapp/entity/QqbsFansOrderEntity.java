package com.ygg.webapp.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

public class QqbsFansOrderEntity  extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Float withdraw;
	private Float price;
	
	private BigInteger number;
	private Timestamp create_time;
	private String fans_nickname;
	private Integer fans_account_id;
	private Float withdraw_cash;
	private String image;
	
	public Float getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(Float withdraw) {
		this.withdraw = withdraw;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public BigInteger getNumber() {
		return number;
	}
	public void setNumber(BigInteger number) {
		this.number = number;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getFans_nickname() {
		return fans_nickname;
	}
	public void setFans_nickname(String fans_nickname) {
		this.fans_nickname = fans_nickname;
	}
	public Integer getFans_account_id() {
		return fans_account_id;
	}
	public void setFans_account_id(Integer fans_account_id) {
		this.fans_account_id = fans_account_id;
	}
	public Float getWithdraw_cash() {
		return withdraw_cash;
	}
	public void setWithdraw_cash(Float withdraw_cash) {
		this.withdraw_cash = withdraw_cash;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "QqbsFansOrderEntity [withdraw=" + withdraw + ", price=" + price + ", number=" + number
				+ ", create_time=" + create_time + ", fans_nickname=" + fans_nickname + ", fans_account_id="
				+ fans_account_id + ", withdraw_cash=" + withdraw_cash + ", image=" + image + "]";
	}
	
	
}
