package com.ygg.webapp.controller.wechat;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ygg.webapp.service.wechat.WeChatMenuService;
import com.ygg.webapp.util.CommonConstant;
import com.ygg.webapp.util.CommonHttpClient;
import com.ygg.webapp.util.CommonUtil;
import com.ygg.webapp.util.WeixinMessageDigestUtil;
import com.ygg.webapp.wechat.message.event.EventMessage;

@Controller
@RequestMapping("/wechatMenu")
public class WeChatMenuController {

	  @Resource(name = "weChatMenuService")
	  private WeChatMenuService weChatMenuService;

	  @RequestMapping(value = "/create", method=RequestMethod.GET)
	  public void createMenu() {
		
		  weChatMenuService.createMenu();
	  }
}
