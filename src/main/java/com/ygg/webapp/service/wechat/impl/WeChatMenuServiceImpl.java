package com.ygg.webapp.service.wechat.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;

import com.ygg.webapp.service.wechat.WeChatMenuService;
import com.ygg.webapp.util.CommonConstant;
import com.ygg.webapp.util.WeixinMessageDigestUtil;

@Service("weChatMenuService")
public class WeChatMenuServiceImpl implements WeChatMenuService {

	public void createMenu() {

		JSONObject	json 	= new JSONObject();
		JSONArray	buttons	= new JSONArray();
		
		JSONObject	btn1	= new JSONObject();
		
		btn1.put("type", "view");
//		bt
		
		String menu = "{\"buttons\":[{\"type\":\"view\",\"name\":\"心动好货\",\"url\":\"http://dew.waibao58.com/cilu-web/hqbsHomeInfo/getHomeInfo\"},{\"type\":\"view\",\"name\":\"我的订单\",\"url\":\"http://dew.waibao58.com/cilu-web/order/list/0\"},{\"name\":\"行动指南\",\"sub_button\":[{\"type\":\"view\",\"name\":\"了解心动慈露\",\"url\":\"http://b2.waibao.help/cilu/article/ciluintroduce.html\"},{\"type\":\"view\",\"name\":\"有问题找客服\",\"url\":\"http://b2.waibao.help/cilu/article/contactcilu.html\"}]}]}";
		/** 获取平台accessToken */
		String appid = CommonConstant.APPID;
		String secret = CommonConstant.APPSECRET;
		String accessToken = WeixinMessageDigestUtil.getAccessToken(appid, secret);
		System.out.println(accessToken);
		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
		try {
			URL url = new URL(action);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			System.out.println(conn);
			conn.setRequestProperty("content-Type", "multipart/form-data");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");

			conn.connect();
			OutputStream os = conn.getOutputStream();
			os.write(menu.getBytes("utf-8"));
			os.flush();
			os.close();

			InputStream is = conn.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println(message);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
