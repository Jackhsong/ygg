package com.ygg.webapp.service;

import java.util.List;

import com.ygg.webapp.entity.AccountWithdrawCashEntity;
import com.ygg.webapp.exception.ServiceException;

public interface AccountWithdrawCashService {

	/*
	 * 查询用户的id和用户的所有奖励金额
	 */
	List<AccountWithdrawCashEntity> findAccountWithdrawCash()
			throws ServiceException;
}
