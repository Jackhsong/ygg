package com.ygg.webapp.dao;

import com.ygg.webapp.entity.AccountEntity;
import com.ygg.webapp.exception.DaoException;

public interface UserPhoneBindDao {

	public Integer findAccountIdByPhone(String mobile_number)throws DaoException;
	
	public void updatePhoneById(AccountEntity accountEntity)throws DaoException;
	
}
