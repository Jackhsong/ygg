package com.ygg.webapp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.QqbsFansOrderDao;
import com.ygg.webapp.entity.QqbsFansOrderEntity;
import com.ygg.webapp.exception.ServiceException;
import com.ygg.webapp.service.QqbsFansOrderService;

@Service("qqbsFansOrderService")
public class QqbsFansOrderServiceImpl implements QqbsFansOrderService{

    @Resource(name = "qqbsFansOrderDao")
    private QqbsFansOrderDao qqbsFansOrderDao;
    	
	@Override
	public QqbsFansOrderEntity findSumPriceByAccountId(int account_id) throws ServiceException {
		QqbsFansOrderEntity qqbsFansOrderEntity=qqbsFansOrderDao.findSumPriceByAccountId(account_id);
		return qqbsFansOrderEntity;
	}

	@Override
	public List<QqbsFansOrderEntity> findAllOrderMessages(int account_id) throws ServiceException {
		List<QqbsFansOrderEntity> list=qqbsFansOrderDao.findAllOrderMessages(account_id);
		return list;
	}

}
