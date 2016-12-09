package com.ygg.webapp.dao;

import java.util.List;

import com.ygg.webapp.entity.AccountWithdrawCashEntity;
import com.ygg.webapp.exception.DaoException;

public interface AccountWithdrawCashDao {
    /*
     * 查询qqbs_fans_order表格中的用户id和总共奖励的金额
     */
	List<AccountWithdrawCashEntity> findAccountWithdrawCash()
	    throws DaoException;
	
}
