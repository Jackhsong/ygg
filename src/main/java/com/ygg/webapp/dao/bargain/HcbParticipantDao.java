package com.ygg.webapp.dao.bargain;

import java.util.List;

import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;;

public interface HcbParticipantDao {
	
	int deleteByPrimaryKey(Integer fakeId);

    int insert(HcbParticipant record);

    int insertSelective(HcbParticipant record);

    HcbParticipant selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(HcbParticipant record);

    int updateByPrimaryKey(HcbParticipant record);
    
    HcbParticipant selectByUseUuid(String userUuid);
    
    List<HcbParticipant> listSelectByBargainerUuid(String bargainerUuid);
}
