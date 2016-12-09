package com.ygg.webapp.entity;

/*
 * 所有的用户id以及用户的奖励金额
 */
public class AccountWithdrawCashEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private float withdraw;
	private int id;
	
	public float getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(float withdraw) {
		this.withdraw = withdraw;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "AccountWithdrawCashEntity [withdraw=" + withdraw + ", id=" + id + "]";
	}
		
}
