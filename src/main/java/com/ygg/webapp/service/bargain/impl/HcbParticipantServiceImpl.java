package com.ygg.webapp.service.bargain.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.bargain.HcbParticipantDao;
import com.ygg.webapp.entity.bargain.HcbParticipant;
import com.ygg.webapp.service.bargain.HcbParticipantService;

@Service("HcbParticipantService")
public class HcbParticipantServiceImpl implements HcbParticipantService {

	@Resource
	HcbParticipantDao participants;

	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return participants.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(HcbParticipant record) {
		return participants.insert(record);
	}

	@Override
	public int insertSelective(HcbParticipant record) {
		return participants.insertSelective(record);
	}

	@Override
	public HcbParticipant selectByPrimaryKey(Integer fakeId) {
		return participants.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(HcbParticipant record) {
		return participants.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HcbParticipant record) {
		return participants.updateByPrimaryKey(record);
	}

	@Override
	public HcbParticipant selectByUseUuid(String userUuid) {
		return participants.selectByUseUuid(userUuid);
	}

	@Override
	public List<HcbParticipant> listSelectByBargainerUuid(String userUuid) {
		return participants.listSelectByBargainerUuid(userUuid);
	}
}
