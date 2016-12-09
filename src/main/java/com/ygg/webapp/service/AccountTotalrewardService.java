package com.ygg.webapp.service;

import com.ygg.webapp.entity.reward.QqbsRewardEntity;
import com.ygg.webapp.exception.ServiceException;

public interface AccountTotalrewardService {

	/*
	 * 这个方法是用来根据用户的id查询用户总共的奖励金额
	 */
	Float findAccountTotalrewardById(int account_id) throws ServiceException;
		
	/*
	 * 这个方法是用来向表格中插入一条数据
	 */
	void insertIntoWithdrawCash(QqbsRewardEntity qqbsRewardEntity) throws ServiceException;
	
	/*
	 * 这个方法是用来更改表格中的数据
	 */
	void updateWithdrawCashById(QqbsRewardEntity qqbsRewardEntity) throws ServiceException;
	
	
	QqbsRewardEntity toInsertIntoWithdrawCash(int account_id,Float total_reward,float withdraw_cash) throws ServiceException;
	
	QqbsRewardEntity toUpdateWithdrawCash(int account_id,float withdraw_cash) throws ServiceException;
	
	
}
