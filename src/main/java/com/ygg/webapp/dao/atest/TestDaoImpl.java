package com.ygg.webapp.dao.atest;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.atest.TestEntity;
import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;

@Repository("testDao")
public class TestDaoImpl extends BaseDaoImpl implements TestDao{

	@Override
	public TestEntity findMessageById(String account_id) throws Exception {
		TestEntity testentity=this.getSqlSession().selectOne("TestMapper.findMessageById",account_id);
		return testentity;
	}

	@Override
	public Float findTotalRewardByid(String account_id) throws Exception {
		
		return this.getSqlSession().selectOne("TestMapper.findTotalRewardByid",account_id);
	}

	@Override
	public Float findWithdrawCashByid(String account_id) throws Exception {
		
		return this.getSqlSession().selectOne("TestMapper.findWithdrawCashByid",account_id);
	}

	@Override
	public Integer findPointByid(String account_id) throws Exception {

		return this.getSqlSession().selectOne("TestMapper.findPointByid",account_id);
	}

	@Override
	public TestEntity findFansCountByid(String account_id) throws Exception {

		return this.getSqlSession().selectOne("TestMapper.findFansCountByid",account_id);
	}

	@Override
	public String findFansFatherByid(String account_id) throws Exception {
		
		return this.getSqlSession().selectOne("TestMapper.findFansFatherByid",account_id);
	}

	@Override
	public HcbBargain findBargainByAccountid(String account_id) throws Exception {
		
		return this.getSqlSession().selectOne("TestMapper.findBargainByAccountid",account_id);
	}

	@Override
	public void insertIntoBargainMessage(HcbBargain hcbBargain) throws Exception {
		
		this.getSqlSession().insert("TestMapper.insertIntoBargainMessage",hcbBargain);
		
	}

	@Override
	public Integer findSumNumberByActivityId(Integer activityid) throws Exception {

		return this.getSqlSession().selectOne("TestMapper.findSumNumberByActivityId",activityid);
	}

	@Override
	public Integer findAllBargainPeople(Integer activityid) throws Exception {
	
		return this.getSqlSession().selectOne("TestMapper.findAllBargainPeople",activityid);
	}

	@Override
	public List<HcbParticipant> findListParticipant(HcbBargain hcbBargain) throws Exception {
		List<HcbParticipant> list=this.getSqlSession().selectList("TestMapper.findListParticipant",hcbBargain);
		return list;
	}

	@Override
	public String findGoodsPriceByid(HcbParticipant hcbParticipant) throws Exception {
		
		return this.getSqlSession().selectOne("TestMapper.findGoodsPriceByid",hcbParticipant);
	}

	@Override
	public int findCountNumberByid(HcbBargain hcbBargain) throws Exception {
		
		return this.getSqlSession().selectOne("TestMapper.findCountNumberByid",hcbBargain);
	}

	@Override
	public void updateBargainMessage(HcbBargain hcbBargain) throws Exception {
		this.getSqlSession().update("TestMapper.updateBargainMessage",hcbBargain);
		
	}

	@Override
	public void insertIntoParticipantMessage(HcbParticipant hcbParticipant) throws Exception {
		this.getSqlSession().insert("TestMapper.insertIntoParticipantMessage",hcbParticipant);
		
	}
	
	

}
