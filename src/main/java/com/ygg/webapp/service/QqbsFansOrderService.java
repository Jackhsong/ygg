package com.ygg.webapp.service;

import java.util.List;

import com.ygg.webapp.entity.QqbsFansOrderEntity;
import com.ygg.webapp.exception.ServiceException;

public interface QqbsFansOrderService {

	QqbsFansOrderEntity findSumPriceByAccountId(int account_id)throws ServiceException;
	
	List<QqbsFansOrderEntity> findAllOrderMessages(int account_id)throws ServiceException;
	
}
