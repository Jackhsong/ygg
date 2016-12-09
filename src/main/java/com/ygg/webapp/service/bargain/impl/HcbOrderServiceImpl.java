package com.ygg.webapp.service.bargain.impl;

import com.ygg.webapp.entity.OrderEntity;
import com.ygg.webapp.service.bargain.HcbOrderService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.OrderDao;

@Service("HcbOrderService")
public class HcbOrderServiceImpl implements HcbOrderService {

	@Resource
	OrderDao orderDao;
	
	public int insertOrder(OrderEntity order) {
		
		return orderDao.addOrder(order);
	}
}
