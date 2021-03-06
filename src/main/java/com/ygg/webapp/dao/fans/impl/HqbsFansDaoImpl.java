
 /**************************************************************************
 * Copyright (c) 2014-2016 浙江格家网络技术有限公司.
 * All rights reserved.
 * 
 * 项目名称：心动慈露APP
 * 版权说明：本软件属浙江格家网络技术有限公司所有，在未获得浙江格家网络技术有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.ygg.webapp.dao.fans.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ygg.webapp.dao.fans.HqbsFansDao;
import com.ygg.webapp.dao.impl.mybatis.base.BaseDaoImpl;
import com.ygg.webapp.entity.fans.QqbsFansEntity;
import com.ygg.webapp.exception.DaoException;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:zhangld@yangege.com">zhangld</a>
  * @version $Id: HqbsFansDaoImpl.java 12586 2016-05-23 07:16:23Z qiuyibo $   
  * @since 2.0
  */
@Repository("hqbsFansDao")
public class HqbsFansDaoImpl extends BaseDaoImpl implements HqbsFansDao {
	
	public int insertFans(QqbsFansEntity fans)throws DaoException{
		return this.getSqlSession().insert("HqbsFansMapper.insertFans", fans);
	}
	public int getFansCount(Map<String, Object> para)throws DaoException{
		return this.getSqlSession().selectOne("HqbsFansMapper.getFansCount",para);
	}
	
	public List<QqbsFansEntity> getFansList(Map<String, Object> para)throws DaoException{
		return this.getSqlSession().selectList("HqbsFansMapper.getFansList", para);
	}
	
	/**
	 * level "1,2,3"
	 * @param para
	 * @return
	 * @see com.ygg.webapp.dao.fans.HqbsFansDao#getFansLists(java.util.Map)
	 */
	public List<QqbsFansEntity> getFansLists(Map<String, Object> para){
        return this.getSqlSession().selectList("HqbsFansMapper.getFansLists", para);
    }
	
	/**
	 * 获取粉丝订单数
	 * @param accountId
	 * @return
	 * @throws DaoException
	 */
	public int getFansOrderCount(int accountId)throws DaoException{
		return this.getSqlSession().selectOne("HqbsFansMapper.getFansOrderCount", accountId);
	}
	/**
	 * 获取粉丝订单总金额
	 * @param accountId
	 * @return
	 * @throws DaoException
	 */
	public float getFansOrderPrice(int accountId)throws DaoException{
		return this.getSqlSession().selectOne("HqbsFansMapper.getFansOrderPrice", accountId);
	}
}
