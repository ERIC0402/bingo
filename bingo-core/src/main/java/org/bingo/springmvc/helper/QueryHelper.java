/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.springmvc.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.bingo.common.collection.CollectUtils;
import org.bingo.common.util.StrUtils;
import org.bingo.hibernate.model.query.Condition;
import org.bingo.hibernate.model.query.page.Page;
import org.bingo.hibernate.model.query.page.PageLimit;
import org.bingo.hibernate.query.builder.OqlBuilder;
import org.bingo.springmvc.util.CookieUtils;

public class QueryHelper {

	protected static final Logger logger = LoggerFactory.getLogger(QueryHelper.class);

	public static final String PAGENO = "pageNo";

	public static final String PAGESIZE = "pageSize";

	public static boolean RESERVED_NULL = true;

	public static void populateConditions(OqlBuilder<?> builder) {
		builder.where(extractConditions(builder.getEntityClass(), builder.getAlias(), null));
	}

	/**
	 * 把entity alias的别名的参数转换成条件.<br>
	 * 
	 * @param entityQuery
	 * @param exclusiveAttrNames
	 *            以entityQuery中alias开头的属性串
	 */
	public static void populateConditions(OqlBuilder<?> entityQuery, String exclusiveAttrNames) {
		entityQuery.where(extractConditions(entityQuery.getEntityClass(), entityQuery.getAlias(),
				exclusiveAttrNames));
	}
	
	public static void populateIds(OqlBuilder<?> entityQuery, String paramName) {
		Long[] ids =  StrUtils.splitToLong(Params.get(paramName + "s"));
		if(ids != null && ids.length > 0){
			entityQuery.where(paramName + " in(:" + paramName.replaceAll("\\.", "") + "s)", Arrays.asList(ids));
		}
	}

	/**
	 * 提取中的条件
	 * 
	 * @param clazz
	 * @param prefix
	 * @param exclusiveAttrNames
	 * @return
	 */
	public static List<Condition> extractConditions(Class<?> clazz, String prefix, String exclusiveAttrNames) {
		Object entity = null;
		try {
			entity = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("[RequestUtil.extractConditions]: error in in initialize " + clazz);
		}
		List<Condition> conditions = CollectUtils.newArrayList();
		Map<String, Object> params = Params.sub(prefix, exclusiveAttrNames);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String attr = entry.getKey();
			String strValue = entry.getValue().toString().trim();
			// 过滤空属性
			if (StringUtils.isNotEmpty(strValue)) {
				try {
					if (RESERVED_NULL && "null".equals(strValue)) {
						conditions.add(new Condition(prefix + "." + attr + " is null"));
					} else {
						StringBuilder sb = new StringBuilder();
						String[] keys = attr.split("\\|");
						Condition condition = new Condition(null);
						for(String key : keys){
							if(sb.length() > 0){
								sb.append(" or ");
							};
							String namedParam = key.replace('.', '_').replace('|', '_');
							BeanUtils.copyProperty(entity, key, strValue);
							Object settedValue = PropertyUtils.getProperty(entity, key);
							if (null == settedValue) continue;
							if (settedValue instanceof String) {
								sb.append(prefix).append(".").append(key).append(" like :").append(namedParam);
								settedValue = "%" + settedValue + "%";
							} else {
								sb.append(prefix).append(".").append(key).append(" = :").append(namedParam);
							}
							condition.param(settedValue);
						}
						condition.setContent(sb.toString());
						conditions.add(condition);
					}
				} catch (Exception e) {
					logger.debug("[populateFromParams]:error in populate entity " + prefix + "'s attribute "
							+ attr);
				}
			}
		}
		return conditions;
	}

	/**
	 * 从的参数或者cookie中(参数优先)取得分页信息
	 * 
	 * @return
	 */
	public static PageLimit getPageLimit() {
		PageLimit limit = new PageLimit();
		limit.setPageNo(getPageNo());
		limit.setPageSize(getPageSize());
		return limit;
	}

	/**
	 * 获得请求中的页码
	 * 
	 * @return
	 */
	public static int getPageNo() {
		String pageNo = Params.get(PAGENO);
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNumeric(pageNo)) {
			return Integer.valueOf(pageNo.trim()).intValue();
		} else {
			return Page.DEFAULT_PAGE_NUM;
		}
	}

	/**
	 * 获得请求中的页长
	 * 
	 * @return
	 */
	public static int getPageSize() {
		String pageSize = Params.get(PAGESIZE);
		if (StringUtils.isNotBlank(pageSize) && StringUtils.isNumeric(pageSize)) {
			return Integer.valueOf(pageSize.trim()).intValue();
		} else {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			pageSize = CookieUtils.getCookieValue(request, PAGESIZE);
			if (StringUtils.isNotEmpty(pageSize) && StringUtils.isNumeric(pageSize)) {
				return Integer.valueOf(pageSize).intValue();
			} else return Page.DEFAULT_PAGE_SIZE;
		}
	}

	public static void addDateIntervalCondition(OqlBuilder<?> query, String attr, String beginOn, String endOn) {
		addDateIntervalCondition(query, query.getAlias(), attr, beginOn, endOn);
	}

	/**
	 * 增加日期区间查询条件
	 * 
	 * @param
	 * @param query
	 * @param alias
	 * @param attr
	 *            时间限制属性
	 * @param beginOn
	 *            开始的属性名字(全名)
	 * @param endOn
	 *            结束的属性名字(全名)
	 * @throws ParseException
	 */
	public static void addDateIntervalCondition(OqlBuilder<?> query, String alias, String attr,
			String beginOn, String endOn) {
		String stime = Params.get(beginOn);
		String etime = Params.get(endOn);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = null, edate = null;
		if (StringUtils.isNotBlank(stime)) {
			try {
				sdate = df.parse(stime);
			} catch (Exception e) {
				logger.debug("wrong date format:" + stime);
			}

		}
		// 截至日期增加一天
		if (StringUtils.isNotBlank(etime)) {
			try {
				edate = df.parse(etime);
			} catch (Exception e) {
				logger.debug("wrong date format:" + etime);
			}
			if (null != edate) {
				Calendar gc = new GregorianCalendar();
				gc.setTime(edate);
				gc.set(Calendar.DAY_OF_YEAR, gc.get(Calendar.DAY_OF_YEAR) + 1);
				edate = gc.getTime();
			}
		}
		String objAttr = ((null == alias) ? query.getAlias() : alias) + "." + attr;
		if (null != sdate && null == edate) {
			query.where(objAttr + " >=:sdate", sdate);
		} else if (null != sdate && null != edate) {
			query.where(objAttr + " >=:sdate and " + objAttr + " <:edate", sdate, edate);
		} else if (null == sdate && null != edate) {
			query.where(objAttr + " <:edate", edate);
		}
	}
}
