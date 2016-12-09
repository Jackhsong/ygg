package com.ygg.webapp.controller.bargain;

import com.ygg.webapp.service.bargain.HcbParticipantService;
import com.ygg.webapp.util.CommonConstant;
import com.ygg.webapp.util.SessionUtil;
import com.ygg.webapp.util.WeixinMessageDigestUtil;
import com.ygg.webapp.util.YggWebProperties;
import com.ygg.webapp.util.CommonHttpClient;
import com.ygg.webapp.view.AccountView;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.tools.ant.taskdefs.Nice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ygg.webapp.service.bargain.HcbBargainService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;
import com.ygg.webapp.entity.AccountEntity;
import com.ygg.webapp.entity.QqbsAccountEntity;
import com.ygg.webapp.service.AccountService;
import com.ygg.webapp.service.account.HqbsAccountService;

@Controller
@RequestMapping("bargain/participant")
public class HcbParticipantController {

	@Resource
	HcbBargainService bargainService;
	
	@Resource
	HcbParticipantService participantService;
	
	@Resource
	AccountService	accountService;
	
	@Resource
	HqbsAccountService hqbsAccountService;
	
	@RequestMapping("/bargain")
	@ResponseBody
	public String bargain(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(required = true) String bargainerUuid)
	throws Exception {
		
		JSONObject	json = new JSONObject();
		
		AccountView av = SessionUtil.getCurrentUser(request.getSession());
		
		Double currentPrice = null;
		Double cutPrice = null;
		HcbBargain	bargain = null;
		
		if (bargainerUuid != null) {
			
			bargain = bargainService.selectByBargainerUuid(bargainerUuid);
		}
		
		currentPrice	= Double.valueOf(bargain.getCurrentPrice());
		
		if (currentPrice <= 1.0) {
			
			json.put("code", "1");
			json.put("dsp", "该好友已达最低价格");
			
			return json.toJSONString();
		}
		
		List<HcbParticipant> participants = participantService.listSelectByBargainerUuid(bargainerUuid);
		
		for (HcbParticipant participant : participants) {
			
			if (participant.getUserUuid().equals(String.valueOf(av.getId()))) {
				
				json.put("code", "2");
				json.put("dsp", "您已经帮好友砍过价了");
		
				return json.toJSONString();
			}
		}
		
		int preNumber = (int)(Math.random()*800);
		cutPrice	= (double)preNumber / 100.0 + 2.0;
		
		if (bargainerUuid.equals("164640306")) {
			
			preNumber = (int)(Math.random()*100);
			cutPrice	= (double)preNumber / 100.0 + 7.0;
		}
		
		currentPrice -= cutPrice;
		
		if (currentPrice < 1) {
			
			cutPrice	= (double) (Float.valueOf(bargain.getCurrentPrice()) - 1.0);
			currentPrice = (double) 1.0;
			bargain.setIsBargain(1);
		}
		
		bargain.setCurrentPrice(String.valueOf(currentPrice));
		bargainService.updateByPrimaryKeySelective(bargain);
		
		HcbParticipant	participant		= new HcbParticipant();
		
		participant.setCreateDatetime(new Date());
		participant.setUserUuid(String.valueOf(av.getId()));
		participant.setGoodsPrice(String.format("%.2f", cutPrice));
		participant.setBargainerUuid(bargainerUuid);
		participant.setAvatar(av.getImage());
		participant.setFullName(av.getNickname());
		
		int result = participantService.insertSelective(participant);
		
		if (result == 1) {
			
			String baseDefaultUrl = YggWebProperties.getInstance().getProperties("base_default_url");
			
			String appid = CommonConstant.APPID;
		    String secret = CommonConstant.APPSECRET;
			
			String token = WeixinMessageDigestUtil.getAccessToken(appid, secret);
			
			QqbsAccountEntity accountEntity = hqbsAccountService.getAccountByAccountId(Integer.valueOf(bargain.getBargainerUuid()));
			
			JSONArray	articleList = new JSONArray();
			
			JSONObject 	article1	= new JSONObject();

			article1.put("title", "帮" + av.getNickname() + "也砍一刀");
			article1.put("url", baseDefaultUrl + "bargain/activity_b?accountId=" + String.valueOf(av.getId()));
			article1.put("picurl", baseDefaultUrl + "resources/bargain/images/back_knife.jpg");

			articleList.add(article1);
			
			JSONObject 	article2	= new JSONObject();

			article2.put("title", "你对我很重要");
			article2.put("url", baseDefaultUrl + "bargain/activity_b?accountId=" + String.valueOf(av.getId()));
			article2.put("picurl", av.getImage());

			articleList.add(article2);
			
			JSONObject 	article3	= new JSONObject();

			article3.put("title", "下次活动预告：能洗手的洗洁精");
			article3.put("url", baseDefaultUrl + "bargain/next_activity");
			article3.put("picurl", baseDefaultUrl + "resources/bargain/images/next_activity.jpg");

			articleList.add(article3);
			
			CommonHttpClient.articleMessageCustomSend(token, accountEntity.getOpenId(), articleList);
			
			json.put("code", "0");
			json.put("price", String.format("%.2f", cutPrice));
		} else {
			
			json.put("code", "1");
			json.put("dsp", "fuck");
		}
		
		return json.toJSONString();
	}
}
