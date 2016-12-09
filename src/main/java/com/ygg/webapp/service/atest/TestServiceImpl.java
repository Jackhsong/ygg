package com.ygg.webapp.service.atest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ygg.webapp.dao.atest.TestDao;
import com.ygg.webapp.entity.atest.TestEntity;
import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;

@Service("testService")
public class TestServiceImpl implements TestService{
	
	@Resource(name="testDao")
	private TestDao testdao;
	

	@Override
	public TestEntity findMessageById(String account_id) throws Exception {
		TestEntity testentity=testdao.findMessageById(account_id);
		
		return testentity;
	}


	@Override
	public Float findTotalRewardByid(String account_id) throws Exception {
		
		return testdao.findTotalRewardByid(account_id);
	}


	@Override
	public Float findWithdrawCashByid(String account_id) throws Exception {

		return testdao.findWithdrawCashByid(account_id);
	}


	@Override
	public Integer findPointByid(String account_id) throws Exception {
		
		return testdao.findPointByid(account_id);
	}


	@Override
	public TestEntity findFansCountByid(String account_id) throws Exception {

		return testdao.findFansCountByid(account_id);
	}


	@Override
	public String findFansFatherByid(String account_id) throws Exception {

		return testdao.findFansFatherByid(account_id);
	}


	@Override
	public HcbBargain findBargainByAccountid(String account_id) throws Exception {
		
		return testdao.findBargainByAccountid(account_id);
	}


	@Override
	public void insertIntoBargainMessage(HcbBargain hcbBargain) throws Exception {
	     testdao.insertIntoBargainMessage(hcbBargain);
		
	}


	@Override
	public Integer findSumNumberByActivityId(Integer activityid) throws Exception {
		
		return testdao.findSumNumberByActivityId(activityid);
	}


	@Override
	public Integer findAllBargainPeople(Integer activityid) throws Exception {

		return testdao.findAllBargainPeople(activityid);
	}


	@Override
	public List<HcbParticipant> findListParticipant(HcbBargain hcbBargain) throws Exception {
		
		return testdao.findListParticipant(hcbBargain);
	}


	@Override
	public String findGoodsPriceByid(HcbParticipant hcbParticipant) throws Exception {
		
		return testdao.findGoodsPriceByid(hcbParticipant);
	}


	@Override
	public int findCountNumberByid(HcbBargain hcbBargain) throws Exception {
		
		return testdao.findCountNumberByid(hcbBargain);
	}


	@Override
	public void updateBargainMessage(HcbBargain hcbBargain) throws Exception {
		testdao.updateBargainMessage(hcbBargain);
		
	}


	@Override
	public void insertIntoParticipantMessage(HcbParticipant hcbParticipant) throws Exception {
		testdao.insertIntoParticipantMessage(hcbParticipant);
		
	}

}
