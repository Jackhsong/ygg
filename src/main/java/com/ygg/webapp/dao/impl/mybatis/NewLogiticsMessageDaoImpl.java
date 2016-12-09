package com.ygg.webapp.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.NewLogiticsMessageDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.NewLogisticsMessageEntity;
import com.ygg.webapp.exception.DaoException;

@Repository("newLogiticsMessageDao")
public class NewLogiticsMessageDaoImpl extends BaseDaoImpl implements NewLogiticsMessageDao {

	@Override
	public List<NewLogisticsMessageEntity> findAllLogisticsMessages() throws DaoException {

		return this.getSqlSession().selectList("NewLogisticsMessageMapper.findAllLogisticsMessages");
	}

	@Override
	public List<String> findAllLogistcsMessagesNumber() throws DaoException {
		
		return this.getSqlSession().selectList("NewLogisticsMessageMapper.findAllLogistcsMessagesNumber");
	}

	@Override
	public int insertIntoNoMessages(NewLogisticsMessageEntity newLogisticsMessageEntity) throws DaoException {
		return this.getSqlSession().insert("NewLogisticsMessageMapper.insertIntoNoMessages",newLogisticsMessageEntity);

	}

	@Override
	public List<String> findLogisticsMessageByNumber(String number) throws DaoException {

		return this.getSqlSession().selectList("NewLogisticsMessageMapper.findLogisticsMessageByNumber",number);
	}



}
