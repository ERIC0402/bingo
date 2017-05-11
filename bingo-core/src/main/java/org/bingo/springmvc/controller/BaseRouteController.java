package org.bingo.springmvc.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.bingo.hibernate.model.Entity;
import org.bingo.hibernate.model.TimeEntity;
import org.bingo.hibernate.model.query.Order;
import org.bingo.hibernate.model.query.page.PageLimit;
import org.bingo.hibernate.query.QueryBuilder;
import org.bingo.hibernate.query.builder.OqlBuilder;
import org.bingo.springmvc.convention.Message;
import org.bingo.springmvc.helper.QueryHelper;

public class BaseRouteController extends BaseEntityController {
	
	@RequestMapping("/index")
	public void index() {

	}

	@RequestMapping(value = { "/list", "/search" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void search() {
		put(getShortName() + "s", search(getQueryBuilder()));
	}
	
	@RequestMapping("edit")
	public void edit() {
		Long entityId = getEntityId(getShortName());
		Entity<?> entity = null;
		if (null == entityId) {
			entity = populateEntity();
		} else {
			entity = getModel(getEntityName(), entityId);
		}
		put(getShortName(), entity);
		editSetting(entity);
	}
	
	protected QueryBuilder<?> getQueryBuilder() {
		OqlBuilder<?> builder = OqlBuilder.from(getEntityName(), getShortName());
		populateConditions(builder);
		QueryHelper.populateIds(builder, getShortName() + ".id");
		builder.orderBy(getOrderString()).limit(getPageLimit());
		return builder;
	}
	
	protected void editSetting(Entity<?> entity) {

	}
	
	@RequestMapping("save")
	public String save() throws Exception {
		return saveAndForward(populateEntity());
	}
	
	@RequestMapping("remove")
	public String remove() throws Exception {
		Long entityId = getEntityId(getShortName());
		Collection<?> entities = null;
		if (null == entityId) {
			entities = getModels(getEntityName(), getEntityIds(getShortName()));
		} else {
			Entity<?> entity = getModel(getEntityName(), entityId);
			entities = Collections.singletonList(entity);
		}
		return removeAndForward(entities);
	}
	
	protected int getPageNo() {
		return QueryHelper.getPageNo();
	}

	protected int getPageSize() {
		return QueryHelper.getPageSize();
	}

	/**
	 * 从request的参数或者cookie中(参数优先)取得分页信息
	 * 
	 * @param request
	 * @return
	 */
	protected PageLimit getPageLimit() {
		return QueryHelper.getPageLimit();
	}

	protected void populateConditions(OqlBuilder<?> builder) {
		QueryHelper.populateConditions(builder);
	}

	protected void populateConditions(OqlBuilder<?> builder, String exclusiveAttrNames) {
		QueryHelper.populateConditions(builder, exclusiveAttrNames);
	}

	/**
	 * 获取排序(无参数，默认按照ID倒序排列)
	 * @return
	 */
	protected String getOrderString() {
		return getOrderString(getDefaultOrderString());
	}

	protected String getDefaultOrderString() {
		return getShortName() + ".id desc";
	}

	/**
	 * 获取排序
	 * 
	 * @param defaultOrder
	 *            默认排序字段
	 * @return
	 */
	protected String getOrderString(String defaultOrder) {
		String orderBy = get(Order.ORDER_STR);
		if (StringUtils.isEmpty(orderBy)) {
			orderBy = defaultOrder;
		}
		return orderBy;
	}
	
	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @return
	 */
	protected String saveAndForward(Entity<?> entity) {
		try {
			if (entity instanceof TimeEntity<?>) {
				TimeEntity<?> timeEntity = (TimeEntity<?>) entity;
				if (!timeEntity.isPersisted()) {
					timeEntity.setCreatedAt(new Date());
				}
				timeEntity.setUpdatedAt(new Date());
			}
			saveOrUpdate(entity);
			if(saveOrUpdateAfter(entity)) {
				return redirect("list", "info.save.success");
			}else {
				return redirect("list", new Message(Message.ERROR, "info.save.failure"));
			}
		} catch (Exception e) {
			logger.info("saveAndForwad failure", e);
			return redirect("list", new Message(Message.ERROR, "info.save.failure"));
		}

	}

	protected String removeAndForward(Collection<?> entities) {
		try {
			remove(entities);
		} catch (Exception e) {
			logger.info("removeAndForwad failure", e);
			return redirect("list", new Message(Message.ERROR, "info.save.failure"));
		}
		return redirect("list", "info.remove.success");
	}
	
}
