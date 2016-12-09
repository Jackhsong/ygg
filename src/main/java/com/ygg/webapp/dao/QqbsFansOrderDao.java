package com.ygg.webapp.dao;

import java.util.List;

import com.ygg.webapp.entity.QqbsFansOrderEntity;
import com.ygg.webapp.exception.DaoException;

public interface QqbsFansOrderDao {

	QqbsFansOrderEntity findSumPriceByAccountId(int account_id)throws DaoException;
	
	List<QqbsFansOrderEntity> findAllOrderMessages(int account_id)throws DaoException;
}
