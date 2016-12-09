package com.ygg.webapp.service.atest;

import java.util.List;

import com.ygg.webapp.entity.atest.TestEntity;
import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;

public interface TestService {
	
	public TestEntity findMessageById(String account_id) throws Exception;
	
	public Float findTotalRewardByid(String account_id) throws Exception;
	
	public Float findWithdrawCashByid(String account_id) throws Exception;
	
	public Integer findPointByid(String account_id) throws Exception;
	
	public TestEntity findFansCountByid(String account_id) throws Exception;
	
	public String findFansFatherByid(String account_id) throws Exception;
	
	public HcbBargain findBargainByAccountid(String account_id)throws Exception;
	
	public void insertIntoBargainMessage(HcbBargain hcbBargain)throws Exception;
	
	public Integer findSumNumberByActivityId(Integer activityid) throws Exception;
	
	public Integer findAllBargainPeople(Integer activityid) throws Exception;
	
	public List<HcbParticipant> findListParticipant(HcbBargain hcbBargain) throws Exception ;
	
	public String findGoodsPriceByid(HcbParticipant hcbParticipant)throws Exception;
	
	public int findCountNumberByid(HcbBargain hcbBargain)throws Exception;
	
	public void updateBargainMessage(HcbBargain hcbBargain) throws Exception;
	
	public void insertIntoParticipantMessage(HcbParticipant hcbParticipant) throws Exception;

}
