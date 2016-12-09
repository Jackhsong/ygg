package com.ygg.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ygg.webapp.sdk.sendMessage.SDKTestSendTemplateSMS;

@Controller("userRegistController")
@RequestMapping("/toUserRegist")
public class UserRegistController {

	//跳转页面，用于跳转到用手机号登陆页面
	@RequestMapping("/userRegist")
	@ResponseBody
	public ModelAndView toUserRegist(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/user/userRegist");
		
		return mv;
	}
	
	 
	//发送登陆短信
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
	
	
	
	
}
