/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.query.util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bingo.common.collection.CollectUtils;
import org.bingo.hibernate.model.Entity;
import org.bingo.hibernate.model.query.Condition;
import org.bingo.hibernate.predicates.ValidEntityKeyPredicate;

/**
 * 条件提取辅助类
 * 
 * @author chaostone
 */
public final class ConditionUtils {
	private static final Logger logger = LoggerFactory.getLogger(ConditionUtils.class);

	private ConditionUtils() {
		super();
	}

	public static String toQueryString(final List<Condition> conditions) {
		if (null == conditions || conditions.isEmpty()) { return ""; }
		final StringBuilder buf = new StringBuilder("");
		for (final Iterator<Condition> iter = conditions.iterator(); iter.hasNext();) {
			final Condition con = iter.next();
			buf.append('(').append(con.getContent()).append(')');
			if (iter.hasNext()) {
				buf.append(" and ");
			}
		}
		return buf.toString();
	}

	/**
	 * 提取对象中的条件<br>
	 * 提取的属性仅限"平面"属性(允许包括component)<br>
	 * 过滤掉属性:null,或者空Collection
	 * 
	 * @param alias
	 * @param entity
	 * @param mode
	 * @return
	 */
	public static List<Condition> extractConditions(final String alias, final Entity<?> entity) {
		if (null == entity) { return Collections.emptyList(); }
		final List<Condition> conditions = new ArrayList<Condition>();

		StringBuilder aliasBuilder = new StringBuilder(alias == null ? "" : alias);
		if (aliasBuilder.length() > 0 && !alias.endsWith(".")) {
			aliasBuilder.append(".");
		}
		String attr = "";
		try {
			@SuppressWarnings("unchecked")
			final Set<String> props = PropertyUtils.describe(entity).keySet();
			for (final Iterator<String> iter = props.iterator(); iter.hasNext();) {
				attr = iter.next();
				// 条件描述的应该是属性
				if (!PropertyUtils.isWriteable(entity, attr)) {
					continue;
				}
				final Object value = PropertyUtils.getProperty(entity, attr);
				if (null == value) {
					continue;
				}
				if (!(value instanceof Collection<?>)) {
					addAttrCondition(conditions, alias + attr, value);
				}
			}
		} catch (Exception e) {
			logger.debug("error occur in extractConditions for  bean {} with attr named {}", entity, attr);
		}
		return conditions;
	}

	/**
	 * 获得条件的绑定参数映射
	 * 
	 * @param query
	 * @param conditions
	 */
	public static Map<String, Object> getParamMap(final List<Condition> conditions) {
		final Map<String, Object> params = new HashMap<String, Object>();
		for (final Condition con : conditions) {
			params.putAll(getParamMap(con));
		}
		return params;
	}

	/**
	 * 获得条件的绑定参数映射
	 * 
	 * @param query
	 * @param conditions
	 */
	public static Map<String, Object> getParamMap(final Condition condition) {
		final Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.contains(condition.getContent(), "?")) {
			final List<String> paramNames = condition.getParamNames();
			if (paramNames.size() > condition.getParams().size()) { throw new RuntimeException(
					"condition params not setted [" + condition.getContent() + "] with value:"
							+ condition.getParams()); }
			for (int i = 0; i < paramNames.size(); i++) {
				params.put(paramNames.get(i), condition.getParams().get(i));
			}
		}
		return params;
	}

	/**
	 * 为extractConditions使用的私有方法<br>
	 * 
	 * @param conditions
	 * @param name
	 * @param value
	 * @param mode
	 */
	private static void addAttrCondition(final List<Condition> conditions, final String name, Object value) {
		if (value instanceof String) {
			if (StringUtils.isBlank((String) value)) { return; }
			StringBuilder content = new StringBuilder(name);
			content.append(" like :").append(name.replace('.', '_'));
			conditions.add(new Condition(content.toString(), "%" + value + "%"));
		} else if (value instanceof Component) {
			conditions.addAll(extractComponent(name, (Component) value));
			return;
		} else if (value instanceof Entity<?>) {
			try {
				final String key = "id";
				Object property = PropertyUtils.getProperty(value, key);
				if (ValidEntityKeyPredicate.getInstance().evaluate(property)) {
					StringBuilder content = new StringBuilder(name);
					content.append('.').append(key).append(" = :").append(name.replace('.', '_')).append('_')
							.append(key);
					conditions.add(new Condition(content.toString(), property));
				}
			} catch (Exception e) {
				logger.warn("getProperty " + value + "error", e);
			}
		} else {
			conditions.add(new Condition(name + " = :" + name.replace('.', '_'), value));
		}
	}

	private static List<Condition> extractComponent(final String prefix, final Component component) {
		if (null == component) { return Collections.emptyList(); }
		final List<Condition> conditions = CollectUtils.newArrayList();
		String attr = "";
		try {
			@SuppressWarnings("unchecked")
			final Set<String> props = PropertyUtils.describe(component).keySet();
			for (final Iterator<String> iter = props.iterator(); iter.hasNext();) {
				attr = iter.next();
				if ("class".equals(attr)) {
					continue;
				}
				if (!PropertyUtils.isWriteable(component, attr)) {
					continue;
				}
				final Object value = PropertyUtils.getProperty(component, attr);
				if (value == null) {
					continue;
				} else if (value instanceof Collection<?>) {
					if (((Collection<?>) value).isEmpty()) {
						continue;
					}
				} else {
					addAttrCondition(conditions, prefix + "." + attr, value);
				}
			}

		} catch (Exception e) {
			logger.warn("error occur in extractComponent of component:" + component + "with attr named :"
					+ attr);
		}
		return conditions;
	}

}
