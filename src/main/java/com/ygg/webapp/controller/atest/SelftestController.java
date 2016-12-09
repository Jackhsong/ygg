package com.ygg.webapp.controller.atest;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.java.swing.plaf.motif.resources.motif;
import com.ygg.webapp.entity.atest.TestEntity;
import com.ygg.webapp.service.atest.TestService;
import com.ygg.webapp.service.atest.TestServiceImpl;

@Controller
@RequestMapping("/selftestcontroller")
public class SelftestController {
	
	private static Logger log=Logger.getLogger(TestServiceImpl.class);
	
	@Resource(name="testService")
	private TestService testservice;

	
	/**
	 * 跳转页面，用于跳转到主页面
	 * @return
	 */
	@RequestMapping("/test1")
	@ResponseBody
	public ModelAndView toLoginFootPage(){
		ModelAndView mv=new ModelAndView("atest/login");
		
		return mv;	
	}
	
	/**
	 * 跳转到用户行动派页面
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public ModelAndView toLoginMycenter(){
		ModelAndView mv=new ModelAndView("atest/mycenter");
		String account_id="164643275"; 
		try {
			TestEntity testentity1=testservice.findMessageById(account_id);
			//查询用户个人信息，头像和昵称
			mv.addObject("account_id",account_id);
			mv.addObject("image",testentity1.getImage());
			mv.addObject("nickName",testentity1.getNickname());
			
			//查询用户的历史累计奖励
			Float totalreward=testservice.findTotalRewardByid(account_id);
			if(totalreward==null){
				mv.addObject("Reward",0);
			}else{
				mv.addObject("Reward",totalreward);
			}
			
			//查询用户的可提现金额
			Float withdrawcash=testservice.findWithdrawCashByid(account_id);
			if(withdrawcash==null){
				mv.addObject("withdrawCash",0);
			}else{
				mv.addObject("withdrawCash",withdrawcash);
			}
			
			//查询用户行动币
			Integer point=testservice.findPointByid(account_id);
			if(point==null){
				mv.addObject("point",0);
			}else{
				mv.addObject("point",point);
			}
			
			//查新用户粉丝人数和粉丝总销量
			TestEntity testentity2=testservice.findFansCountByid(account_id);
			mv.addObject("fans",testentity2.getFans());
			mv.addObject("fansOrderPrice",testentity2.getAllreward());
			
			//查询用户的推荐人
			String recommendedPerson=testservice.findFansFatherByid(account_id);
			mv.addObject("recommendedPerson",recommendedPerson);
			
		} catch (Exception e) {
			log.error("------------------查询个人信息失败！---------------------",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	
	
	/**
	 * 我的二维码，查询用户的二维码
	 */
	@RequestMapping("/getQrCode")
	@ResponseBody
	public ModelAndView getQrCode(){
		
		
		
		return null;
	}
	
	
	/**
	 *我的粉丝页面
	 */
	@RequestMapping("/getFansLists")
	@ResponseBody
	public ModelAndView getFansLists(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/atest/fanslist");;
		mv.addObject("oneCount",1);
		mv.addObject("twoThCount",2);
		mv.addObject("count",2);
		return mv;
	}
	
	
	
}


