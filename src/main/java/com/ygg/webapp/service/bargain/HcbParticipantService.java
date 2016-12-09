package com.ygg.webapp.service.bargain;

import java.util.List;

import com.ygg.webapp.entity.bargain.HcbParticipant;

public interface HcbParticipantService {

	   public	int deleteByPrimaryKey(Integer fakeId);

	   public   int insert(HcbParticipant record);

	   public  int insertSelective(HcbParticipant record);

	   public HcbParticipant selectByPrimaryKey(Integer fakeId);

	   public int updateByPrimaryKeySelective(HcbParticipant record);

	   public int updateByPrimaryKey(HcbParticipant record);
	   
	   public HcbParticipant selectByUseUuid(String userUuid);

	   public List<HcbParticipant> listSelectByBargainerUuid(String userUuid);
}
