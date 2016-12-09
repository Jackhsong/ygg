package com.ygg.webapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ygg.webapp.entity.AccountWithdrawCashEntity;
import com.ygg.webapp.entity.reward.QqbsRewardEntity;
import com.ygg.webapp.service.AccountTotalrewardService;
import com.ygg.webapp.service.AccountWithdrawCashService;

@Controller("accountWithdrawCashCheckController")
@RequestMapping("/accountWithdrawCash")
public class AccountWithdrawCashCheckController {

	//引入qqbs_fans_order业务层，查询出用户的Id和用户的所有奖励金额
	@Resource(name="accountWithdrawCashService")
	private AccountWithdrawCashService accountWithdrawCashService;
	
	//引入qqbs_reward业务层，根据用户的id查询出用户的所有奖励金额
	@Resource(name="accountTotalrewardService")
	private AccountTotalrewardService accountTotalrewardService;
	
	
	/*
	 * 此方法是用来比较两个表格中的奖励金额，若没有相应的用户，就增加用户数据，若奖励金额不相等
	 * 更改qqbs_reward表格中的数据，同时更改表格中的可领取金额，已领取金额数据不变
	 */
	@RequestMapping("/accountWithdrawCashCheck")
	@ResponseBody
	public void accountWithdrawCashCheck(){
			List<AccountWithdrawCashEntity> list=accountWithdrawCashService.findAccountWithdrawCash();
			for(AccountWithdrawCashEntity accountWithdrawCashEntity:list){
				int account_id=accountWithdrawCashEntity.getId();
				float withdraw_cash=accountWithdrawCashEntity.getWithdraw();
				Float total_reward=accountTotalrewardService.findAccountTotalrewardById(account_id);
				//查询出数据进行核对，如果相等，就不做处理
				//如果查询出的数据为空，就说明没有这个id，需要把这个id插入到qqbs_fans_order表格中
				if(total_reward==null){
					QqbsRewardEntity qqbsRewardEntity=accountTotalrewardService.toInsertIntoWithdrawCash(account_id, total_reward, withdraw_cash);
					accountTotalrewardService.insertIntoWithdrawCash(qqbsRewardEntity);				

				}
				//如果查询的数据不为空，而且两个数据不相等，就需要更改第二个表格中的数据
				if(total_reward!=null&&(total_reward.floatValue())!=withdraw_cash){
					QqbsRewardEntity qqbsRewardEntity=accountTotalrewardService.toUpdateWithdrawCash(account_id, withdraw_cash);
					accountTotalrewardService.updateWithdrawCashById(qqbsRewardEntity);

				}
			}		
	}

}
