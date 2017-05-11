/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.common.populator.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bingo.common.converters.Converter;
import org.bingo.common.populator.Populator;
import org.bingo.common.populator.helper.ReflectHelper;

public class PopulatorImpl implements Populator {

	protected static final Logger logger = LoggerFactory.getLogger(PopulatorImpl.class);

	public static final boolean TRIM_STR = true;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private BeanUtilsBean beanUtils;

	public PopulatorImpl() {
		beanUtils = new BeanUtilsBean(Converter.getDefault());
	}

	public PopulatorImpl(ConvertUtilsBean convertUtils) {
		beanUtils = new BeanUtilsBean(convertUtils);
	}

	/**
	 * 初始化对象指定路径的属性。<br>
	 * 例如给定属性a.b.c,方法会依次检查a a.b a.b.c是否已经初始化
	 * 
	 * @param attr
	 * @param target
	 * @return 初始化完成对象及其类型
	 */
	public Object initProperty(final Object target, String entityName, final String attr) {
		Object propObj = target;
		Class<?> propClass = target.getClass();
		Object property = null;

		int index = 0;
		String[] attrs = StringUtils.split(attr, ".");
		while (index < attrs.length) {
			try {
				property = PropertyUtils.getProperty(propObj, attrs[index]);
				Class<?> propertyType = ReflectHelper.getProperty(propClass, attrs[index]);
				// 初始化
				if (null == propertyType) {
					logger.error("Cannot find property type [{}] of {}", attrs[index], propObj.getClass());
					throw new RuntimeException("Cannot find property type " + attrs[index] + " of " + propObj.getClass().getName());
				}
				if (null == property) {
					property = propertyType.newInstance();
					PropertyUtils.setProperty(propObj, attrs[index], property);
				}
				index++;
				propObj = property;
				propClass = propertyType;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return property;
	}

	/**
	 * 安静的拷贝属性，如果属性非法或其他错误则记录日志
	 * 
	 * @param target
	 * @param attr
	 * @param value
	 */
	public void populateValue(final Object target, String entityName, final String attr, Object value) {
		try {
			if (attr.indexOf('.') > -1) {
				initProperty(target, entityName, StringUtils.substringBeforeLast(attr, "."));
			}
			// 修改Bug，将Date格式化成字符串
			if (value instanceof Date) {
				Date date = (Date) value;
				value = sdf.format(date);
			}
			// 支持中文布尔值导入：是、否
			if ("是".equals(value) || "否".equals(value)) {
				Class<?> type = beanUtils.getPropertyUtils().getPropertyDescriptor(target, attr).getPropertyType();
				if(Boolean.class.equals(type) || "boolean".equals(type.getName())){
					if("是".equals(value)){
						value = "1";
					}else{
						value = "0";
					}
				}
			}
			beanUtils.copyProperty(target, attr, value);
		} catch (Exception e) {
			logger.error("copy property failure:[class:" + entityName + " attr:" + attr + " value:" + value + "]:", e);
		}
	}

	public void populateValue(Object target, String attr, Object value) {
		populateValue(target, target.getClass().getName(), attr, value);
	}

	public Object populate(Object target, Map<String, Object> params) {
		return populate(target, target.getClass().getName(), params);
	}

	public Object populate(String entityName, Map<String, Object> params) {
		try {
			return populate(Class.forName(entityName).newInstance(), entityName, params);
		} catch (Exception ee) {
			throw new RuntimeException(ee.getMessage());
		}
	}

	public Object populate(Class<?> entityClass, Map<String, Object> params) {
		try {
			return populate(entityClass.newInstance(), entityClass.getName(), params);
		} catch (Exception ee) {
			throw new RuntimeException(ee.getMessage());
		}
	}

	/**
	 * 将params中的属性([attr(string)->value(object)]，放入到实体类中。<br>
	 * 如果引用到了别的实体，那么<br>
	 * 如果params中的id为null，则将该实体的置为null.<br>
	 * 否则新生成一个实体，将其id设为params中指定的值。 空字符串按照null处理
	 * 
	 * @param params
	 * @param entity
	 */
	public Object populate(Object entity, String entityName, Map<String, Object> params) {
		for (final Map.Entry<String, Object> paramEntry : params.entrySet()) {
			String attr = paramEntry.getKey();
			Object value = paramEntry.getValue();
			if (value instanceof String) {
				if (StringUtils.isEmpty((String) value)) {
					value = null;
				} else if (TRIM_STR) {
					value = ((String) value).trim();
				}
			}
			// 普通属性
			if (-1 == attr.indexOf('.')) {
				setValue(attr, value, entity);
			} else {
				String parentAttr = StringUtils.substring(attr, 0, attr.lastIndexOf('.'));
				try {
					Object ot = initProperty(entity, entityName, parentAttr);
					if (null == ot) {
						logger.error("error attr:[" + attr + "] value:[" + value + "]");
						continue;
					}
					setValue(attr, value, entity);
				} catch (Exception e) {
					logger.error("error attr:[" + attr + "] value:[" + value + "]", e);
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("populate attr:[" + attr + "] value:[" + value + "]");
			}
		}
		return entity;
	}

	private void setValue(final String attr, final Object value, final Object target) {
		try {
			beanUtils.copyProperty(target, attr, value);
		} catch (Exception e) {
			logger.error("copy property failure:[class:" + target.getClass().getName() + " attr:" + attr + " value:" + value + "]:", e);
		}
	}
}
