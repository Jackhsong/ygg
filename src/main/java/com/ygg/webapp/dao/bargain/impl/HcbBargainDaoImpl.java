package com.ygg.webapp.dao.bargain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.MapperConfig.Base;
import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.bargain.HcbBargainDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.bargain.HcbBargain;

@Repository("HcbBargainDao")
public class HcbBargainDaoImpl extends BaseDaoImpl implements HcbBargainDao {

	public int deleteByPrimaryKey(Integer fakeId) {

		int result = getSqlSession().delete("HcbBargainMapper.deleteByPrimaryKey", fakeId);

		return result;
	}

	public int insert(HcbBargain record) {

		int result = getSqlSession().insert("HcbBargainMapper.insert", record);

		return result;
	}

	public int insertSelective(HcbBargain record) {
		
		int result = getSqlSession().insert("HcbBargainMapper.insertSelective", record);
		
		return result;
	}

	public HcbBargain selectByPrimaryKey(Integer fakeId) {
		
		HcbBargain	bargain	= getSqlSession().selectOne("HcbBargainMapper.selectByPrimaryKey", fakeId);
		
		return bargain;
	}

	public int updateByPrimaryKeySelective(HcbBargain record) {
		
		int result = getSqlSession().update("HcbBargainMapper.updateByPrimaryKeySelective", record);
		
		return result;
	}

 	public int updateByPrimaryKey(HcbBargain record) {
 		
 		int result = getSqlSession().update("HcbBargainMapper.updateByPrimaryKey", record);
 		
 		return result;
 	}

	public HcbBargain selectByBargainerUuid(String bargainerUuid) {
		
		HcbBargain bargain = getSqlSession().selectOne("HcbBargainMapper.selectByBargainerUuid", bargainerUuid);
		
		return bargain;
	}

	public List<HcbBargain> listSelectByUserUuid(String userUuid) {
		
		List<HcbBargain> resultList	= getSqlSession().selectList("HcbBargainMapper.listSelectByUserUuid", userUuid);
		
		return resultList;
	}
	
	public Integer countTotalPayed() {
		
		Integer count = getSqlSession().selectOne("HcbBargainMapper.countTotalPayed");
		
		return count;
	}
	
	public Integer countTotalBargained(String originalPrice) {
		
		Integer count = getSqlSession().selectOne("HcbBargainMapper.countTotalBargained", originalPrice);
		
		return count;
	}
}
