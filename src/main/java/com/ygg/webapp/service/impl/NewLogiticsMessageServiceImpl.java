package com.ygg.webapp.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qq.connect.utils.json.JSONArray;
import com.qq.connect.utils.json.JSONObject;
import com.ygg.webapp.dao.NewLogiticsMessageDao;
import com.ygg.webapp.entity.NewLogisticsMessageEntity;
import com.ygg.webapp.sdk.logisticsType.KdniaoTrackQueryAPI;
import com.ygg.webapp.service.NewLogiticsMessageService;
import com.ygg.webapp.util.StringCheckUtil;

@Service("newLogiticsMessageService")
public class NewLogiticsMessageServiceImpl implements NewLogiticsMessageService {
	
	@Resource(name = "newLogiticsMessageDao")
	private NewLogiticsMessageDao newLogiticsMessageDao;
	
	//查询所有的订单物流状况
	@Override
	public List<NewLogisticsMessageEntity> findAllLogisticsMessages(){
		List<NewLogisticsMessageEntity> list=new ArrayList<NewLogisticsMessageEntity>();
		list=newLogiticsMessageDao.findAllLogisticsMessages();
		return list;
	}

	@Override
	public List<NewLogisticsMessageEntity> checkAllLogisticsMessages(List<NewLogisticsMessageEntity> list){
		//更加物流公司来确定物流的简称，通过快递鸟接口进行查询，查询物流状态
		String yuantong1="圆通";String yuantong2="圆通速递";
		String shentong1="申通";String shentong2="申通快递";
		String zhongtong1="中通";String zhongtong2="中通速递";
		String shunfeng1="顺丰";String shunfeng2="顺丰快递";
		String suer1="速尔";String suer2="速尔快递";
		String yuantongkuaidi="圆通快递";
		List<String> numberlist=newLogiticsMessageDao.findAllLogistcsMessagesNumber();
		KdniaoTrackQueryAPI kdniaoTrackQueryAPI=new KdniaoTrackQueryAPI();		
		List<NewLogisticsMessageEntity> allListmessages=new ArrayList<NewLogisticsMessageEntity>();
		
		for(NewLogisticsMessageEntity entity:list){
			String channel=entity.getChannel();
			if(channel.matches(yuantong1)||channel.matches(yuantong2)){
				entity.setLogg("YTO");
			}
			if(channel.matches(shentong1)||channel.matches(shentong2)){
				entity.setLogg("STO");
			}
			if(channel.matches(zhongtong1)||channel.matches(zhongtong2)){
				entity.setLogg("ZTO");
			}
			if(channel.matches(shunfeng1)||channel.matches(shunfeng2)){
				entity.setLogg("SF");
			}
			if(channel.matches(suer1)||channel.matches(suer2)){
				entity.setLogg("SURE");
			}
			if(channel.matches(yuantongkuaidi)){
				entity.setLogg("GTO");
			}
			if(entity.getLogg()==null){
				entity.setIsinside(2);
		    }
			if(entity.getLogg()!=null){
			   entity.setIsinside(1);
			   for(String str:numberlist){
				  if(entity.getNumber().matches(str)){
					   entity.setIsinside(0);				
				  }	
			   }
			}
		}

		//根据物流单号和物流公司简称查询相关的物流详情，然后将JSON结果转换成字符串
		try {
		for(NewLogisticsMessageEntity entity:list){			
			String result=kdniaoTrackQueryAPI.kdniao(entity.getLogg(),entity.getNumber());
			List<Map<String, Object>> newarraylist = new ArrayList<Map<String, Object>>();
			JSONArray array = new JSONObject(result).getJSONArray("Traces");
			String[] fields={"AcceptStation","AcceptTime"};
			for (int i = 0; i < array.length(); i++) {
	            JSONObject object = (JSONObject)array.opt(i); 
	            Map<String, Object> map = new HashMap<String, Object>();  
	            for (String str : fields) {  
	                map.put(str, object.get(str));  
	            }  
	            newarraylist.add(map);  
	        } 
			//将查询出的物流信息详情全部放入集合中
			for(int i=0;i<newarraylist.size();i++){
				NewLogisticsMessageEntity newmessage=new NewLogisticsMessageEntity();
				newmessage.setIsinside(entity.getIsinside());
				newmessage.setNumber(entity.getNumber());
				newmessage.setChannel(entity.getChannel());
				
				Map<String,Object> map = newarraylist.get(i);
				newmessage.setContent((String) map.get("AcceptStation"));
				String stringtime=(String) map.get("AcceptTime");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date utiltime=sdf.parse(stringtime);
				Timestamp sqltime = new Timestamp(utiltime.getTime());
				newmessage.setOperatetime(sqltime);
				allListmessages.add(newmessage);
			}
	       } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		   return allListmessages;
	}

	
		/**
		
		List<NewLogisticsMessageEntity> nomessageentitylist=new ArrayList<NewLogisticsMessageEntity>(); 
		//没有快递详情的
		for(NewLogisticsMessageEntity entity:NoMessageList){			
			String result=kdniaoTrackQueryAPI.kdniao(entity.getLogg(),entity.getNumber());
			List<Map<String, Object>> newarraylist = new ArrayList<Map<String, Object>>();
			JSONArray array = new JSONObject(result).getJSONArray("Traces");
			String[] fields={"AcceptStation","AcceptTime"};
			for (int i = 0; i < array.length(); i++) {
	            JSONObject object = (JSONObject)array.opt(i); 
	            Map<String, Object> map = new HashMap<String, Object>();  
	            for (String str : fields) {  
	                map.put(str, object.get(str));  
	            }  
	            newarraylist.add(map);  
	        } 
			
			for(int i=0;i<newarraylist.size();i++){
				NewLogisticsMessageEntity newmessage=new NewLogisticsMessageEntity();
				newmessage.setNumber(entity.getNumber());
				newmessage.setChannel(entity.getChannel());
				Map<String,Object> map = newarraylist.get(i);
				newmessage.setContent((String) map.get("AcceptStation"));
				String stringtime=(String) map.get("AcceptTime");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date utiltime=sdf.parse(stringtime);
				Timestamp sqltime = new Timestamp(utiltime.getTime());
				newmessage.setOperatetime(sqltime);
				nomessageentitylist.add(newmessage);
			}
	       } 
		
		已经有快递记录，但是不完整的
		List<NewLogisticsMessageEntity> alreadymessageentitylist=new ArrayList<NewLogisticsMessageEntity>();
		for(NewLogisticsMessageEntity entity:AleadyMessageList){			
			String result=kdniaoTrackQueryAPI.kdniao(entity.getLogg(),entity.getNumber());
			List<Map<String, Object>> newarraylist = new ArrayList<Map<String, Object>>();
			JSONArray array = new JSONObject(result).getJSONArray("Traces");
			String[] fields={"AcceptStation","AcceptTime"};
			for (int i = 0; i < array.length(); i++) {
	            JSONObject object = (JSONObject)array.opt(i); 
	            Map<String, Object> map = new HashMap<String, Object>();  
	            for (String str : fields) {  
	                map.put(str, object.get(str));  
	            }  
	            newarraylist.add(map);  
	        } 
			
			for(int i=0;i<newarraylist.size();i++){
				NewLogisticsMessageEntity newmessage=new NewLogisticsMessageEntity();
				newmessage.setNumber(entity.getNumber());
				newmessage.setChannel(entity.getChannel());
				Map<String,Object> map = newarraylist.get(i);
				newmessage.setContent((String) map.get("AcceptStation"));
				String stringtime=(String) map.get("AcceptTime");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date utiltime=sdf.parse(stringtime);
				Timestamp sqltime = new Timestamp(utiltime.getTime());
				newmessage.setOperatetime(sqltime);
				alreadymessageentitylist.add(newmessage);
			}
			
	       } 
		*/


	
	@Override
	public int insertIntoNoMessages(NewLogisticsMessageEntity newLogisticsMessageEntity) {
		return newLogiticsMessageDao.insertIntoNoMessages(newLogisticsMessageEntity);		
	}

	@Override
	public List<String> findLogisticsMessageByNumber(String number) {
		List<String> list=newLogiticsMessageDao.findLogisticsMessageByNumber(number);
		return list;
	}
	
	
	

	//根据查询的结果比对物流信息，如果存在就跳过，如果不存在就进行添加
	public void checkAndAddMessage(){
		try {
			List<NewLogisticsMessageEntity> list=new ArrayList<NewLogisticsMessageEntity>();
			
			list=this.findAllLogisticsMessages();
			List<NewLogisticsMessageEntity> allmessage=this.checkAllLogisticsMessages(list);
			
			for(NewLogisticsMessageEntity e:allmessage){
				//判断物流信息，如果没有就直接插入
				if(e.getIsinside()==1){
					int updatenumber=this.insertIntoNoMessages(e);					
				}
				//如果已经有这个物流号了，进行判断，这条物流信息是否已经有了
				if(e.getIsinside()==0){
					String number=e.getNumber();
					List<String> allOprenatimelist=this.findLogisticsMessageByNumber(number);
					String content=e.getContent();

					boolean isnot=true;
					for(String str:allOprenatimelist){
						StringCheckUtil stringcheckutil=new StringCheckUtil();
						//调用工具类比较字符串的相似度，不完全一样的话，就说明没有这条信息，进行插入
						float result=stringcheckutil.StringCheckUtil(content, str);						
						if(result==1.0){
							isnot=false;								
						}				
					}
					if(isnot==true){
						int updatenumber=this.insertIntoNoMessages(e);	
					}
				}
			}	
		} catch (Exception e1) {				
			e1.printStackTrace();
		}	
	}
}