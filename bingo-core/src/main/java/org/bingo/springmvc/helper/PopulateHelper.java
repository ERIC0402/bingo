/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.springmvc.helper;

import java.util.Map;

import org.bingo.common.util.PopulatorUtils;
import org.bingo.hibernate.util.EntityUtils;

public class PopulateHelper {

	/**
	 * 将request中的参数设置到clazz对应的bean。
	 * 
	 * @param request
	 * @param clazz
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T populate(Class<T> clazz, String name) {
		try {
			return (T) populate(Class.forName(clazz.getName()).newInstance(), clazz.getName(), name);
		} catch (Exception ee) {
			throw new RuntimeException(ee.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T populate(Class<T> clazz) {
		try {
			return (T) populate(clazz.newInstance(), clazz.getName(), EntityUtils.getCommandName(clazz.getName()));
		} catch (Exception ee) {
			throw new RuntimeException(ee.getMessage());
		}
	}

	public static Object populate(String entityName) {
		try {
			return populate(Class.forName(entityName).newInstance(), entityName, EntityUtils.getCommandName(entityName));
		} catch (Exception ee) {
			throw new RuntimeException(ee.getMessage());
		}
	}

	public static Object populate(String entityName, String name) {
		try {
			return populate(Class.forName(entityName).newInstance(), entityName, name);
		} catch (Exception ee) {
			throw new RuntimeException(ee.getMessage());
		}
	}

	public static Object populate(Object obj, String entityName, String name) {
		Map<String, Object> params = Params.sub(name);
		return PopulatorUtils.populate(obj, entityName, params);
	}
}
