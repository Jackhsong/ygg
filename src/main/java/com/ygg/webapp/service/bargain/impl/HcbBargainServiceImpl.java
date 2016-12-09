package com.ygg.webapp.service.bargain.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.bargain.HcbBargainDao;
import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.service.bargain.HcbBargainService;

@Service("HcbBargainService")
public class HcbBargainServiceImpl implements HcbBargainService {

	@Resource
	HcbBargainDao bargains;

	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return bargains.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(HcbBargain record) {
		return bargains.insert(record);
	}

	@Override
	public int insertSelective(HcbBargain record) {
		return bargains.insertSelective(record);
	}

	@Override
	public HcbBargain selectByPrimaryKey(Integer fakeId) {
		return bargains.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(HcbBargain record) {
		return bargains.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HcbBargain record) {
		return bargains.updateByPrimaryKey(record);
	}

	@Override
	public HcbBargain selectByBargainerUuid(String bargainerUuid) {
		return bargains.selectByBargainerUuid(bargainerUuid);
	}

	@Override
	public List<HcbBargain> listSelectByUserUuid(String userUuid) {
		return bargains.listSelectByUserUuid(userUuid);
	}
	
	@Override
	public Integer countTotalPayed() {
		return bargains.countTotalPayed();
	}
	
	@Override
	public Integer countTotalBargained(String originalPrice) {
		
		return bargains.countTotalBargained(originalPrice);
	}
}
