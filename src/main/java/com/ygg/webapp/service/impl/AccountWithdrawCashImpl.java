package com.ygg.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.AccountWithdrawCashDao;
import com.ygg.webapp.entity.AccountWithdrawCashEntity;
import com.ygg.webapp.exception.ServiceException;
import com.ygg.webapp.service.AccountWithdrawCashService;

@Service("accountWithdrawCashService")
public class AccountWithdrawCashImpl implements AccountWithdrawCashService{

	@Resource(name = "accountWithdrawCashDao")
	private AccountWithdrawCashDao accountWithdrawCashDao;
	
	@Override
	public List<AccountWithdrawCashEntity> findAccountWithdrawCash()throws ServiceException {
		List<AccountWithdrawCashEntity> list=new ArrayList<AccountWithdrawCashEntity>();
	    list=accountWithdrawCashDao.findAccountWithdrawCash();
		return list;
	}

}
