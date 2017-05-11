/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.springmvc.helper;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.bingo.common.collection.MapConverter;
import org.bingo.common.converters.Converter;

public class Params {

	public static final MapConverter converter = new MapConverter(Converter.getDefault());

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getParams() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getParameterMap();
	}

	public static String get(String attr) {
		return converter.getString(getParams(), attr);
	}

	public static Long getLong(String name) {
		return converter.getLong(getParams(), name);
	}

	public static <T> T get(String name, Class<T> clazz) {
		return converter.get(getParams(), name, clazz);
	}

	public static Object[] getAll(String attr) {
		return converter.getAll(getParams(), attr);
	}

	public static <T> T[] getAll(String attr, Class<T> clazz) {
		return converter.getAll(getParams(), attr, clazz);
	}

	public static boolean getBool(String name) {
		return converter.getBool(getParams(), name);
	}

	public static Boolean getBoolean(String name) {
		return converter.getBoolean(getParams(), name);
	}

	public static Date getDate(String name) {
		return converter.getDate(getParams(), name);
	}

	public static java.util.Date getDateTime(String name) {
		return converter.getDateTime(getParams(), name);
	}

	public static Float getFloat(String name) {
		return converter.getFloat(getParams(), name);
	}

	public static Integer getInteger(String name) {
		return converter.getInteger(getParams(), name);
	}

	public static Map<String, Object> sub(String prefix) {
		return converter.sub(getParams(), prefix);
	}

	public static Map<String, Object> sub(String prefix, String exclusiveAttrNames) {
		return converter.sub(getParams(), prefix, exclusiveAttrNames);
	}

}
