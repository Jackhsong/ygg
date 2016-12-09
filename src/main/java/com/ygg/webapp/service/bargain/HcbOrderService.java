package com.ygg.webapp.service.bargain;

import java.util.List;

import org.springframework.core.annotation.Order;

import com.ygg.webapp.entity.OrderEntity;

public interface HcbOrderService {

	public int insertOrder(OrderEntity order);
}
