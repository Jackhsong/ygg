package com.ygg.test.demo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.qq.connect.utils.json.JSONArray;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;
import com.ygg.webapp.sdk.logisticsType.KdniaoTrackQueryAPI;
import com.ygg.webapp.util.StringCheckUtil;


public class Demo {

	//@Test
	//根据快递公司简称和物流号查询物流信息，并将JSON格式转换成String格式
	public void test1() throws JSONException, ParseException{
		 KdniaoTrackQueryAPI kd=new KdniaoTrackQueryAPI();
		 String result=kd.kdniao("YTO","560343190078");   
		 
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 JSONArray array = new JSONObject(result).getJSONArray("Traces");
		 
		 String[] fields={"AcceptStation","AcceptTime"};
		 
		 for (int i = 0; i < array.length(); i++) {  
	            JSONObject object = (JSONObject)array.opt(i); 	            
	            String s=(String)object.get("AcceptTime");
	            
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            java.util.Date acctime=sdf.parse(s);
	           
	            java.sql.Date d=new java.sql.Date(acctime.getTime());
	            Timestamp date = new Timestamp(acctime.getTime());

	            Map<String, Object> map = new HashMap<String, Object>();  
	            for (String str : fields) {  
	                map.put(str, object.get(str));  
	            }  
	            list.add(map);  
	       } 
        } 

	//@Test
	public void test2() throws ParseException{
		String st="2016-08-12 12:12:12";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date acctime=sdf.parse(st);
        System.out.println(acctime);
	}
	
	//@Test
	public void test3() throws ParseException{
	    String s="2016-08-12 12:12:12";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date acctime=sdf.parse(s);
        Timestamp date = new Timestamp(acctime.getTime());
        System.out.println(date);
	}
	
	//@Test
	public void test4(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
		String str = df.format(now);
		System.out.println(str);
		String s1="2016-08-13 00:00:00";
		String s2="2016-08-13 00:00:00";
		System.out.println(s1.equals(s2));
	}
	
    //@Test
	//测试比较两个字符串的相似度情况
    public void test5(){
    	StringCheckUtil str=new StringCheckUtil();
    	String str1="已签收,签收人是:【本人签收】";
    	String str2="已签收,签收人是:【本人签收】";
    	float result=str.StringCheckUtil(str1, str2);
    	if(result==1.0){
    	System.out.println(result);
    	}
    }

    //@Test
    //测试生成固定长度数字，验证码
    public void test6(){
    	String number="";
    	int length=6;
    	Random ran=new Random();
    	for(int i=0;i<length;i++){
    		number+=ran.nextInt(9);
    	}
    	System.out.println(number);   	
    }
    
    
    //@Test
    //测试字符串转换成浮点型数据
    public void test7(){
    	String price="88.00";
    	System.out.println(String.format("%.2f",price));  	
    }


   // @Test
    //测试砍价随机金额
    public void test8(){
    	int preNumber = (int)(Math.random()*800);
		 Double cutPrice= (double)preNumber / 100.0 + 2.0;
		 String cutPriceString=String.format("%.2f",cutPrice);
		 System.out.println(cutPriceString);
		Double d=88.88;
		System.out.println(d/10);
    	
    }

    @Test
    //测试截取字符串长度
    public void test9(){
    	String s="last_trade_no_40051320012016093053983580";
    	String ss=s.substring(14,s.length());
    	System.out.println(ss);
    }






}







