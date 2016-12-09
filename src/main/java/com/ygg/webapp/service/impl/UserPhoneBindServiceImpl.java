package com.ygg.webapp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.UserPhoneBindDao;
import com.ygg.webapp.entity.AccountEntity;
import com.ygg.webapp.exception.ServiceException;
import com.ygg.webapp.service.UserPhoneBindService;

@Service("userPhoneBindService")
public class UserPhoneBindServiceImpl implements UserPhoneBindService{
	
	@Resource(name="userPhoneBindDao")
	private UserPhoneBindDao userPhoneBindDao;

	@Override
	public Integer findAccountIdByPhone(String mobile_number) throws ServiceException {
		Integer appid=userPhoneBindDao.findAccountIdByPhone(mobile_number);
		
		return appid;
	}

	@Override
	public void updatePhoneById(AccountEntity accountEntity) throws ServiceException {
		userPhoneBindDao.updatePhoneById(accountEntity);
		
	}

}
