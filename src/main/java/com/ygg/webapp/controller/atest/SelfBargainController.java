package com.ygg.webapp.controller.atest;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;
import com.ygg.webapp.service.atest.TestService;

@Controller
@RequestMapping("/selfBargainController")
public class SelfBargainController {
	
	@Resource(name="testService")
	private TestService testService;

	@RequestMapping("/activity_a88/{account_id}")
	@ResponseBody
	public ModelAndView activityAtest(@PathVariable("account_id")String account_id) throws Exception{
		Integer acctivityid=21;Integer goodnumber=1000;String currentprice="88.00";
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/atest/bagainA");
		HcbBargain bargain= testService.findBargainByAccountid(account_id);
		//如果此用户是第一次进，就把他的信息插入数据库
		if(bargain==null){
			bargain=new HcbBargain();
			bargain.setBargainerUuid(account_id);
			bargain.setCurrentPrice(currentprice);
			bargain.setCreateDatetime(new Date());
			bargain.setUserUuid(account_id);
			bargain.setPeopleBargain("昵称");
			bargain.setActivityid(acctivityid);
			testService.insertIntoBargainMessage(bargain);			
		}//不是空，那就不是首次进用户
		//将商品的价格转换成有两位小数
		Float CurrentPrice=Float.valueOf(bargain.getCurrentPrice());
		bargain.setCurrentPrice(String.format("%.2f",CurrentPrice));
		mv.addObject("bargain",bargain);
		
		//查询卖出商品数量和剩余库存
        Integer soldnumber=testService.findSumNumberByActivityId(acctivityid);
        Integer leftNumber=goodnumber-soldnumber;
        mv.addObject("soldNumber",soldnumber);
        mv.addObject("leftNumber",leftNumber);
		
		//查询已经砍价的总人数
        Integer bargainerNumber=testService.findAllBargainPeople(acctivityid);
        mv.addObject("bargainerNumber",String.valueOf(bargainerNumber));
        
        //判断商品是否卖完
        if(leftNumber<=0){
        	mv.addObject("isSoldOut","1");
        }else{
        	mv.addObject("isSoldOut","0");
        }
        if(leftNumber<=30){
        	mv.addObject("lessThanThirty",1);
        }else if(leftNumber<=150){
        	mv.addObject("lessThanHundred",1);
        }
        
        //查询用户的帮忙砍价的人的详细信息
        if(bargain!=null && bargain.getBargainerUuid()!=null){
        	List<HcbParticipant> Participant=testService.findListParticipant(bargain);
        	if(Participant!=null&&Participant.size()>0){
        		for(HcbParticipant participant:Participant){
        			if(participant.getUserUuid().equals(account_id)){
        				mv.addObject("isBargained", "1");
        				//判断是否自己给自己砍
        			}
        		}
        		mv.addObject("participantList",Participant);
        	}
        }
		return mv;
	}


	@RequestMapping("/activity_b88/{account_id}")
	@ResponseBody
	public ModelAndView acitivityBtest(@PathVariable("account_id")String account_id)throws Exception{
		Integer acctivityid=21;Integer goodnumber=1000;String currentprice="88.00";
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/atest/bagainB");
		String cut_account_id="164643276";
		if(cut_account_id!=null&&cut_account_id.equals(account_id)){
			mv.setViewName("/atest/bagainA");
			//判断是否自己给自己砍
		}
		HcbBargain bargain= testService.findBargainByAccountid(account_id);
		//如果此用户是第一次进，就把他的信息插入数据库
		if(bargain==null){
			bargain=new HcbBargain();
			bargain.setBargainerUuid(account_id);
			bargain.setCurrentPrice(currentprice);
			bargain.setCreateDatetime(new Date());
			bargain.setUserUuid(account_id);
			bargain.setPeopleBargain("昵称");
			bargain.setActivityid(acctivityid);
			testService.insertIntoBargainMessage(bargain);			
		}//不是空，那就不是首次进用户
		//将商品的价格转换成有两位小数
		Float CurrentPrice=Float.valueOf(bargain.getCurrentPrice());
		bargain.setCurrentPrice(String.format("%.2f",CurrentPrice));
		mv.addObject("bargain",bargain);
		
		//查询卖出商品数量和剩余库存
        Integer soldnumber=testService.findSumNumberByActivityId(acctivityid);
        Integer leftNumber=goodnumber-soldnumber;
        mv.addObject("soldNumber",soldnumber);
        mv.addObject("leftNumber",leftNumber);
		
		//查询已经砍价的总人数
        Integer bargainerNumber=testService.findAllBargainPeople(acctivityid);
        mv.addObject("bargainerNumber",String.valueOf(bargainerNumber));
        
        //判断商品是否卖完
        if(leftNumber<=0){
        	mv.addObject("isSoldOut","1");
        }else{
        	mv.addObject("isSoldOut","0");
        }
        if(leftNumber<=30){
        	mv.addObject("lessThanThirty",1);
        }else if(leftNumber<=150){
        	mv.addObject("lessThanHundred",1);
        }
        
        //查询用户的帮忙砍价的人的详细信息
        if(bargain!=null && bargain.getBargainerUuid()!=null){
        	List<HcbParticipant> Participant=testService.findListParticipant(bargain);
        	if(Participant!=null&&Participant.size()>0){
        		for(HcbParticipant participant:Participant){
        			if(participant.getUserUuid().equals(account_id)){
        				mv.addObject("isBargained", "1");
        				//判断是否自己给自己砍
        			}
        		}
        		mv.addObject("participantList",Participant);
        	}
        }		
		return mv;
	}

	
	
	
	@RequestMapping("/mycut/{account_id}")
	@ResponseBody
	public String mucutPrice(@PathVariable("account_id")String account_id) throws Exception{
		Integer acctivityid=21;Integer goodnumber=1000;String currentprice="88.00";
		String cut_account_id="164640311";
		JSONObject data=new JSONObject();
		HcbBargain bargain=testService.findBargainByAccountid(account_id);
		
		String currentPrice=bargain.getCurrentPrice();
		Double doubleCurrentPrice=Double.valueOf(currentPrice);
		if(doubleCurrentPrice<=1.0){
			 data.put("code","1");
			 data.put("cotent","该好友已达最低价格了！");
			 return data.toJSONString();
		}
		
		HcbParticipant participant=new HcbParticipant();
		participant.setUserUuid(cut_account_id);
		participant.setActivityid(acctivityid);
		participant.setBargainerUuid(account_id);
		String goodsprice=testService.findGoodsPriceByid(participant);
		if(goodsprice!=null){
			 data.put("code","2");
			 data.put("cotent","你已帮好友砍过价了！");
			 return data.toJSONString();
		}		
		int cutPeopleNumber=testService.findCountNumberByid(bargain);
		Double cutPrice1=0.00;
		if(cutPeopleNumber>=0&&cutPeopleNumber<=10){
			cutPrice1=doubleCurrentPrice/10-2;
		}else if(cutPeopleNumber>=10&&cutPeopleNumber<=20){
			cutPrice1=doubleCurrentPrice/10;
		}
		doubleCurrentPrice-=cutPrice1;
		if(doubleCurrentPrice<=1){
			cutPrice1=Double.valueOf(currentPrice)-1.00;
			doubleCurrentPrice=1.00;
			bargain.setIsBargain(1);
		}
		bargain.setCurrentPrice(String.valueOf(doubleCurrentPrice));
		System.out.println(bargain);
		testService.updateBargainMessage(bargain);
		
		String goodspriceString=String.format("%.2f",cutPrice1);
		participant.setGoodsPrice(goodspriceString);
		participant.setFullName("1212");
		participant.setAvatar("http://weishang201605.b0.upaiyun.com/qqbsAccount/wechatImage/F590D4A513590F6F.jpg");
		testService.insertIntoParticipantMessage(participant);
		
		data.put("price",String.format("%.2f",cutPrice1));
		data.put("code","0");

		
		return data.toJSONString();
	}
	
	
	@RequestMapping(value="next_activity")
	@ResponseBody
	public ModelAndView toNextActivity(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/atest/nextBargain");
		
		return mv;
	}


}

















