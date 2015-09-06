/**  
 * @Title: BaseDao.java 
 * @date 2013-7-30 下午01:30:32 
 * @version V1.0   
 */
package ${package}.core.dao.impl;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;

import ${package}.core.dao.BaseDao;
import ${package}.core.utils.LoggerFactary;
import ${package}.core.utils.PropertyUtil;

/**
 * 基础dao实现
 * 
 * @param <T>
 * @author liming
 * @version $Id$
 * @since 2.0
 * @date 2013-6-25 下午04:54:12
 * @updateInfo
 */
public abstract class BaseDaoImpl<T> extends BaseDaoSupport<T> implements
		BaseDao<T> {

	/*** 构造函数 */
	public BaseDaoImpl(Class<T> clazz) {
		super(clazz);
	}

	/** 日志 */
	protected static final Logger logger = LoggerFactary
			.getLogger(BaseDaoImpl.class.getSimpleName());

	/**
	 * 创建po
	 * 
	 * @param t
	 * @throws Exception
	 * @lastUpdateTime 2013-6-25 下午04:44:11
	 * @updateInfo
	 */
	public Serializable createPO(T t) throws Exception {
		try {
			if (PropertyUtil.containsField(t.getClass(), "createTime")) {
				PropertyUtil.setProperty(t, "createTime", new Date());
			}
			if (PropertyUtil.containsField(t.getClass(), "modifyTime")) {
				PropertyUtil.setProperty(t, "modifyTime", new Date());
			}
		} catch (Exception e) {
			logger.error("can't get the property createTime in "
					+ t.getClass().getSimpleName());
		}
		return super.createPO(t);
	}

	/**
	 * 更新po
	 * 
	 * @param t
	 * @lastUpdateTime 2013-6-25 下午04:45:12
	 * @updateInfo
	 */
	public int updatePO(T t) throws Exception {
		try {
			if (PropertyUtil.containsField(t.getClass(), "modifyTime")) {
				PropertyUtil.setProperty(t, "modifyTime", new Date());
			}
		} catch (Exception e) {
			logger.error("can't get the property modifiTime in "
					+ t.getClass().getSimpleName());
		}
		return super.updatePO(t);
	}
}
