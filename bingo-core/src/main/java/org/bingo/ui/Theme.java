/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.ui;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bingo.common.collection.CollectUtils;

public class Theme {

	public static final String DEFAULT_THEME = "xml";

	private final static Map<Class<?>, String> tagNames = CollectUtils.newHashMap();

	private final String name;

	private String uibase;

	public Theme() {
		this.name = DEFAULT_THEME;
	}

	public Theme(String name) {
		super();
		this.name = name;
	}

	public String getTemplatePath(Class<?> clazz, String suffix) {
		StringBuilder sb = new StringBuilder(20);
		sb.append("/templates/").append(name).append('/').append(getTemplateName(clazz)).append(suffix);
		return sb.toString();
	}

	public static String getTemplateName(Class<?> clazz) {
		String name = tagNames.get(clazz);
		if (null == name) {
			name = StringUtils.uncapitalize(clazz.getSimpleName());
			tagNames.put(clazz, name);
		}
		return name;
	}

	public String getName() {
		return name;
	}
	
	public String getUibase() {
		return uibase;
	}

	public void setUibase(String uibase) {
		this.uibase = uibase;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(obj.toString());
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
//		return name.hashCode();
	}
}
