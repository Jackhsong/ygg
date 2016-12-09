package com.ygg.webapp.service;

import java.util.List;

import com.ygg.webapp.entity.NewLogisticsMessageEntity;
import com.ygg.webapp.exception.ServiceException;

public interface NewLogiticsMessageService {

	List<NewLogisticsMessageEntity> findAllLogisticsMessages()throws ServiceException;
	
	List<NewLogisticsMessageEntity> checkAllLogisticsMessages(List<NewLogisticsMessageEntity> list)throws ServiceException;
	
	int insertIntoNoMessages(NewLogisticsMessageEntity newLogisticsMessageEntity)throws ServiceException;
	
	List<String> findLogisticsMessageByNumber(String number)throws ServiceException;
	
    void checkAndAddMessage()throws ServiceException;
	
}
