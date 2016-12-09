package com.ygg.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.ygg.webapp.entity.AccountEntity;
import com.ygg.webapp.sdk.sendMessage.SDKTestSendTemplateSMS;
import com.ygg.webapp.service.UserPhoneBindService;

@Controller
@RequestMapping("/userPhoneBindController")
public class UserPhoneBindController {

	@Resource(name="userPhoneBindService")
	private UserPhoneBindService userPhoneBindService;
	
	//跳转页面，跳转到绑定手机号页面
	@RequestMapping("/toBindPhone/{appid}")
	@ResponseBody
	public ModelAndView toBindPhone(@PathVariable("appid") String appid){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/user/phoneBind");
		mv.addObject("appid",appid);
		return mv;
	}
	
	//绑定手机号
	@RequestMapping("/bindPhone")
	@ResponseBody
	public String bindPhone(@RequestParam(value="phone",required=false,defaultValue="")String phone,
			@RequestParam(value="appid",required=false,defaultValue="")String appid){
		Map<String, Object> data = new HashMap<String, Object>();
		Integer app_id=userPhoneBindService.findAccountIdByPhone(phone);
		if(app_id!=null){
			String stringappid=String.valueOf(app_id);
			if(appid.equals(stringappid)){
				data.put("type",1);
			}else{
				data.put("type",2);
			}
		}else{
			AccountEntity ac=new AccountEntity();
			Integer id=Integer.valueOf(appid);
			ac.setId(id);
			ac.setMobileNumber(phone);
			userPhoneBindService.updatePhoneById(ac);
			data.put("type",3);
		}
		return JSON.toJSONString(data);
	}
	
	
	//发送短信
	@RequestMapping("/sendsms")
	@ResponseBody
	public String sendMessage(@RequestParam(value = "phone", required = false, defaultValue = "") String phone){

		SDKTestSendTemplateSMS sdk=new SDKTestSendTemplateSMS();
		String sendnumberString=sdk.sendMessageToMobile(phone);
	  
	    Map<String, Object> data = new HashMap<String, Object>();
	    data.put("sendnumberString",sendnumberString);
	    data.put("sendMessagePhone", phone);
        return JSON.toJSONString(data);
	}
	
	
	//跳转到登陆页面
	@RequestMapping("/tologin")
	@ResponseBody
	public ModelAndView toUserRegist(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/user/userBind");
		
		return mv;
	}
	
	//判断登陆手机号码是否已绑定
	@RequestMapping("/tocheckphone")
	@ResponseBody
	public String tocheckphone(@RequestParam(value = "phone", required = false, defaultValue = "") String phone){
		JsonObject result = new JsonObject();
		Integer account_id=userPhoneBindService.findAccountIdByPhone(phone);
		if(account_id==null){
			result.addProperty("type",1);
		}else{
			result.addProperty("type",2);
		}
		return result.toString();
	}
	

	
}

