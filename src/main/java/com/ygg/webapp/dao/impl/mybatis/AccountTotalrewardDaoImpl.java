package com.ygg.webapp.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.AccountTotalrewardDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.reward.QqbsRewardEntity;
import com.ygg.webapp.exception.DaoException;

@Repository("accountTotalrewardDao")
public class AccountTotalrewardDaoImpl extends BaseDaoImpl implements AccountTotalrewardDao{

	/*
	 * 此方法是用来实现根据用户的id查询用户总共的奖励金额
	 * @see com.ygg.webapp.dao.AccountTotalrewardDao#findAccountTotalrewardById(int)
	 */
	@Override
	public Float findAccountTotalrewardById(int account_id)throws DaoException {
		
		return this.getSqlSession().selectOne("AccountTotalrewardMapper.findAccountTotalrewardById",account_id);
	}

	/*
	 *此方法是用来给表格qqbs_reward中插入一条数据
	 * @see com.ygg.webapp.dao.AccountTotalrewardDao#insertIntoWithdrawCash(com.ygg.webapp.entity.reward.QqbsRewardEntity)
	 */
	@Override
	public int insertIntoWithdrawCash(QqbsRewardEntity qqbsRewardEntity) throws DaoException {
		 
		return this.getSqlSession().insert("AccountTotalrewardMapper.insertIntoWithdrawCash", qqbsRewardEntity);		
	}

	/*
	 * 此方法是用来根据用户的id更改表格中的总奖励金额，未提现奖励金额
	 * @see com.ygg.webapp.dao.AccountTotalrewardDao#updateWithdrawCashById(com.ygg.webapp.entity.reward.QqbsRewardEntity)
	 */
	@Override
	public int updateWithdrawCashById(QqbsRewardEntity qqbsRewardEntity) throws DaoException {
		
		return this.getSqlSession().update("AccountTotalrewardMapper.updateWithdrawCashById",qqbsRewardEntity);
	}

	
}
