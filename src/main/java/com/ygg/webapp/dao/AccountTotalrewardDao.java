package com.ygg.webapp.dao;

import com.ygg.webapp.entity.reward.QqbsRewardEntity;
import com.ygg.webapp.exception.DaoException;

public interface AccountTotalrewardDao {

	/*
	 * 根据用户的id值查询所有奖励金额
	 */
	Float findAccountTotalrewardById(int account_id)
			throws DaoException;
	
	/*
	 * 给qqbs_reward表格中插入一条数据，含有四个字段，用户id，总金额，已提现，未提现
	 */
	int insertIntoWithdrawCash(QqbsRewardEntity qqbsRewardEntity)
			throws DaoException;
	
	/*
	 * 根据用户id更改表格中的总奖励金额，以及未提现的金额，已提现金额不改
	 */
	int updateWithdrawCashById(QqbsRewardEntity qqbsRewardEntity)
			throws DaoException;
}
