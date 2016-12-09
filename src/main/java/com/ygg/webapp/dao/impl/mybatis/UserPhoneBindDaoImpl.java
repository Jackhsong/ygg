package com.ygg.webapp.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.UserPhoneBindDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.AccountEntity;
import com.ygg.webapp.exception.DaoException;

@Repository("userPhoneBindDao")
public class UserPhoneBindDaoImpl extends BaseDaoImpl implements UserPhoneBindDao{

	@Override
	public Integer findAccountIdByPhone(String mobile_number) throws DaoException {
		
		return this.getSqlSession().selectOne("UserPhoneBindMapper.findAccountIdByPhone",mobile_number);
	}

	@Override
	public void updatePhoneById(AccountEntity accountEntity) throws DaoException {
         this.getSqlSession().update("UserPhoneBindMapper.updatePhoneById", accountEntity);
	
	}
	
	

}
