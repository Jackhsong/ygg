package com.ygg.webapp.service;

import com.ygg.webapp.entity.AccountEntity;
import com.ygg.webapp.exception.ServiceException;

public interface UserPhoneBindService {

	public Integer findAccountIdByPhone(String mobile_number)throws ServiceException;
	
	public void updatePhoneById(AccountEntity accountEntity)throws ServiceException;
}
