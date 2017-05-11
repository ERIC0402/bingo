/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.ui.template.impl;

import java.net.URL;

import freemarker.cache.URLTemplateLoader;

/**
 * 搜索带有固定前缀下的资源
 * 
 */
public class BingoFreemarkerTemplateLoader extends URLTemplateLoader {

	String prefix = null;
	
	public BingoFreemarkerTemplateLoader() {
		super();
	}

	public BingoFreemarkerTemplateLoader(String prefix) {
		super();
		setPrefix(prefix);
	}

	protected URL getURL(String name) {
		URL url = getResource(name, getClass());
		if (null != prefix) {
			url = getResource(prefix + name, getClass());
		}
		return url;
	}
	
	public static URL getResource(String resourceName, Class<?> callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = callingClass.getClassLoader().getResource(resourceName);
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if ((url == null) && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) { 
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String pre) {
		this.prefix = pre;
		if (null != prefix) {
			if (prefix.equals("/")) {
				prefix = null;
			} else {
				if (!prefix.endsWith("/")) {
					prefix += "/";
				}
				if (prefix.startsWith("/")) {
					prefix = prefix.substring(1);
				}
			}
		}
	}
}
