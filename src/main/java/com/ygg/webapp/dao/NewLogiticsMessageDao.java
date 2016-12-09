package com.ygg.webapp.dao;

import java.util.List;

import com.ygg.webapp.entity.NewLogisticsMessageEntity;
import com.ygg.webapp.exception.DaoException;

public interface NewLogiticsMessageDao {

	List<NewLogisticsMessageEntity> findAllLogisticsMessages() throws DaoException;
	
	List<String> findAllLogistcsMessagesNumber()throws DaoException;
	
	int insertIntoNoMessages(NewLogisticsMessageEntity newLogisticsMessageEntity)throws DaoException;
	
	List<String> findLogisticsMessageByNumber(String number)throws DaoException;
	
}
