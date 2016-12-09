package com.ygg.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ygg.webapp.entity.NewLogisticsMessageEntity;
import com.ygg.webapp.service.NewLogiticsMessageService;
import com.ygg.webapp.util.StringCheckUtil;

@Controller("newLogiticsMessageController")
@RequestMapping("/newLogiticsMessage")
public class NewLogiticsMessageController {

	@Resource(name="newLogiticsMessageService")
	private NewLogiticsMessageService newLogiticsMessageService;
	
	
	@RequestMapping("/newLogiticsMessage")
	@ResponseBody
	public void newLogiticsMessage(){
		newLogiticsMessageService.checkAndAddMessage();
		System.out.println("插入物流信息完成！");
		
	}
}
	
/**
		try {
				List<NewLogisticsMessageEntity> list=new ArrayList<NewLogisticsMessageEntity>();
				
				list=newLogiticsMessageService.findAllLogisticsMessages();
				List<NewLogisticsMessageEntity> allmessage=newLogiticsMessageService.checkAllLogisticsMessages(list);
				
				for(NewLogisticsMessageEntity e:allmessage){
					//判断物流信息，如果没有就直接插入
					if(e.getIsinside()==1){
						int updatenumber=newLogiticsMessageService.insertIntoNoMessages(e);					
					}
					//如果已经有这个物流号了，进行判断，这条物流信息是否已经有了
					if(e.getIsinside()==0){
						String number=e.getNumber();
						List<String> allOprenatimelist=newLogiticsMessageService.findLogisticsMessageByNumber(number);
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
							int updatenumber=newLogiticsMessageService.insertIntoNoMessages(e);	
						}
					}
				}	
			} catch (Exception e1) {				
				e1.printStackTrace();
			}			
	}

	
	
}
*/