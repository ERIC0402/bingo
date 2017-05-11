package org.bingo.springmvc.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.bingo.hibernate.EntityDao;
import org.bingo.hibernate.model.Entity;
import org.bingo.hibernate.query.QueryBuilder;
import org.bingo.hibernate.util.EntityUtils;
import org.bingo.springmvc.helper.Params;

public class BaseEntityController extends BaseSecurityController {

	@Autowired
	protected EntityDao entityDao;

	protected String entityName;

	protected String getEntityName() {
		if (null == entityName) {
			throw new RuntimeException("entityName not set for :" + getClass().getName());
		}
		return entityName;
	}

	protected String getShortName() {
		String name = getEntityName();
		if (StringUtils.isNotEmpty(name))
			return EntityUtils.getCommandName(name);
		else
			return null;
	}

	protected Entity<?> getModel(String entityName, Serializable id) {
		return (Entity<?>) entityDao.get(entityName, id);
	}

	@SuppressWarnings("rawtypes")
	protected List getModels(String entityName, Long[] ids) {
		return entityDao.get(entityName, "id", (Object[]) ids);
	}

	protected <T> List<T> getModels(Class<T> modelClass, Long[] ids) {
		return entityDao.get(modelClass, "id", (Object[]) ids);
	}

	@SuppressWarnings("rawtypes")
	protected List search(QueryBuilder<?> query) {
		return entityDao.search(query);
	}

	protected Long getEntityId(String shortName) {
		Long entityId = getLong(shortName + ".id");
		if (null == entityId) {
			entityId = getLong(shortName + "Id");
		}
		if (null == entityId) {
			entityId = getLong(shortName + "id");
		}
		if (null == entityId) {
			entityId = getLong("id");
		}
		return entityId;
	}

	/**
	 * Get entity's id shortname.id[],shortname.ids,shortnameIds
	 * 
	 * @param <T>
	 * @param shortName
	 * @param clazz
	 * @return
	 */
	protected <T> T[] getEntityIds(String shortName, Class<T> clazz) {
		T[] datas = Params.getAll(shortName + ".id", clazz);
		if (null == datas) {
			String datastring = Params.get(shortName + ".ids");
			if (null == datastring)
				datastring = Params.get(shortName + "Ids");
			if (null != datastring) {
				return Params.converter.convert(StringUtils.split(datastring, ","), clazz);
			}
		}
		return datas;
	}

	protected Long[] getEntityIds(String shortName) {
		return getEntityIds(shortName, Long.class);
	}

	protected Long[] getEntityIds() {
		return getEntityIds(getShortName(), Long.class);
	}

	protected void remove(Collection<?> list) {
		entityDao.remove(list);
	}

	protected void remove(Object obj) {
		entityDao.remove(obj);
	}

	protected void saveOrUpdate(Collection<?> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		entityDao.saveOrUpdate(list);
	}

	protected void saveOrUpdate(Object obj) {
		entityDao.saveOrUpdate(obj);
	}
	
	protected boolean saveOrUpdateAfter(Object obj) {
		return true;
	}

	protected Entity<?> populateEntity() {
		return populateEntity(getEntityName(), getShortName());
	}

	protected Entity<?> populateEntity(String entityName, String shortName) {
		Long entityId = getEntityId(shortName);
		Entity<?> entity = null;
		if (null == entityId) {
			entity = (Entity<?>) populate(entityName, shortName);
		} else {
			entity = getModel(entityName, entityId);
			populate(entity, entityName, shortName);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	protected <T> T populateEntity(Class<T> entityClass, String shortName) {
		return (T) populateEntity(entityClass.getName(), shortName);
	}

}
