package com.ygg.webapp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.AccountTotalrewardDao;
import com.ygg.webapp.entity.reward.QqbsRewardEntity;
import com.ygg.webapp.exception.ServiceException;
import com.ygg.webapp.service.AccountTotalrewardService;

@Service("accountTotalrewardService")
public class AccountTotalrewardServiceImpl implements AccountTotalrewardService{
	
	@Resource(name="accountTotalrewardDao")
	private AccountTotalrewardDao accountTotalrewardDao;

	/*
	 * 这个方法是用来根据用户的id值查询用户的所有奖励金额
	 * @see com.ygg.webapp.service.AccountTotalrewardService#findAccountTotalrewardById(int)
	 */
	@Override
	public Float findAccountTotalrewardById(int account_id){
		Float fl=accountTotalrewardDao.findAccountTotalrewardById(account_id);
		return fl;
	}

	/*
	 * 这个方法是用来向表格中插入一条数据
	 * @see com.ygg.webapp.service.AccountTotalrewardService#insertIntoWithdrawCash(com.ygg.webapp.entity.reward.QqbsRewardEntity)
	 */
	@Override
	public void insertIntoWithdrawCash(QqbsRewardEntity qqbsRewardEntity){
		accountTotalrewardDao.insertIntoWithdrawCash(qqbsRewardEntity);
	}

	/*
	 *这个方法是用来更改表格中的数据，根据用户的id更改表格中的总奖励金额
	 * @see com.ygg.webapp.service.AccountTotalrewardService#updateWithdrawCashById(com.ygg.webapp.entity.reward.QqbsRewardEntity)
	 */
	@Override
	public void updateWithdrawCashById(QqbsRewardEntity qqbsRewardEntity){
		accountTotalrewardDao.updateWithdrawCashById(qqbsRewardEntity);
	}

	/*
	 * 这个方法是用来实现表格中没有这条数据的时候，插入这条数据
	 */
	public QqbsRewardEntity toInsertIntoWithdrawCash(int account_id,Float total_reward,float withdraw_cash){
		QqbsRewardEntity qqbsRewardEntity=new QqbsRewardEntity();
		qqbsRewardEntity.setAccountId(account_id);
		qqbsRewardEntity.setTotalReward(withdraw_cash);
		qqbsRewardEntity.setAlreadyCash(0);
		qqbsRewardEntity.setWithdrawCash(withdraw_cash);			
        return qqbsRewardEntity;
	}	
	
	/*
	 * 这个方法是用来实现当两个表格中的数据不一样的时候，更改第二张表格中的数据的
	 * @see com.ygg.webapp.service.AccountTotalrewardService#toUpdateWithdrawCash(int, float)
	 */
	public QqbsRewardEntity toUpdateWithdrawCash(int account_id,float withdraw_cash){
		QqbsRewardEntity qqbsRewardEntity=new QqbsRewardEntity();
		qqbsRewardEntity.setAccountId(account_id);
		qqbsRewardEntity.setTotalReward(withdraw_cash);
		return qqbsRewardEntity;	
	}

	
	
	
	
	
	
}
