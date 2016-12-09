package com.ygg.webapp.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.AccountWithdrawCashDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.AccountWithdrawCashEntity;
import com.ygg.webapp.exception.DaoException;

@Repository("accountWithdrawCashDao")
public class AccountWithdrawCashDaoImpl extends BaseDaoImpl implements AccountWithdrawCashDao {

	@Override
	public List<AccountWithdrawCashEntity> findAccountWithdrawCash() 
		throws DaoException {
		return this.getSqlSession().selectList("AccountWithdrawCashMapper.findAccountWithdrawCash");
	}

}
