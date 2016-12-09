package com.ygg.webapp.dao.bargain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.MapperConfig.Base;
import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.bargain.HcbParticipantDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.bargain.HcbParticipant;

@Repository("HcbParticipantDao")
public class HcbParticipantDaoImpl extends BaseDaoImpl implements HcbParticipantDao {

	public int deleteByPrimaryKey(Integer fakeId) {
		
		int result	= getSqlSession().delete("HcbParticipantMapper.deleteByPrimaryKey", fakeId);
		
		return result;
	}

    public int insert(HcbParticipant record) {
    	
    	int result = getSqlSession().insert("HcbParticipantMapper.insert", record);
    	
    	return result;
    }

    public int insertSelective(HcbParticipant record) {
    	
    	int result = getSqlSession().insert("HcbParticipantMapper.insertSelective", record);
    	
    	return result;
    }

    public HcbParticipant selectByPrimaryKey(Integer fakeId) {
    	
    	HcbParticipant	participant = getSqlSession().selectOne("HcbParticipantMapper.selectByPrimaryKey", fakeId);
    	
    	return participant;
    }

    public int updateByPrimaryKeySelective(HcbParticipant record) {
    	
    	int result = getSqlSession().update("HcbParticipantMapper.updateByPrimaryKeySelective", record);
    	
    	return result;
    }

    public int updateByPrimaryKey(HcbParticipant record) {
    	
    	int result = getSqlSession().update("HcbParticipantMapper.updateByPrimaryKey", record);
    	
    	return result;
    }
    
    public HcbParticipant selectByUseUuid(String userUuid) {
    	
    	HcbParticipant participant = getSqlSession().selectOne("HcbParticipantMapper.selectByUserUuid", userUuid);
    	
    	return participant;
    }
    
    public List<HcbParticipant> listSelectByBargainerUuid(String bargainerUuid) {
    	
    	List<HcbParticipant> participantList = getSqlSession().selectList("HcbParticipantMapper.listSelectByBargainerUuid", bargainerUuid);
    	
    	return participantList;
    }
}
