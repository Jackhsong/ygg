package com.ygg.webapp.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.QqbsFansOrderDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.QqbsFansOrderEntity;
import com.ygg.webapp.exception.DaoException;

@Repository("qqbsFansOrderDao")
public class QqbsFansOrderDaoImpl extends BaseDaoImpl implements QqbsFansOrderDao{

	@Override
	public QqbsFansOrderEntity findSumPriceByAccountId(int account_id) throws DaoException {
		QqbsFansOrderEntity allWithdrawFloat=this.getSqlSession().selectOne("QqbsFansOrderMapper.findSumPriceByAccountId",account_id);

	    return allWithdrawFloat;
	}

	@Override
	public List<QqbsFansOrderEntity> findAllOrderMessages(int account_id) throws DaoException {
		return this.getSqlSession().selectList("QqbsFansOrderMapper.findAllOrderMessages",account_id);
	}

}
