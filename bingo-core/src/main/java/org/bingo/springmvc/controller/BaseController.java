package org.bingo.springmvc.controller;

import java.io.StringWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.bingo.springmvc.helper.Params;
import org.bingo.springmvc.helper.PopulateHelper;
import org.bingo.springmvc.util.CookieUtils;

public class BaseController  {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	// request
	protected String getCookieValue(String cookieName) {
		return CookieUtils.getCookieValue(getRequest(), cookieName);
	}

	protected void addCookie(String name, String value, String path, int age) {
		try {
			CookieUtils.addCookie(getRequest(), getResponse(), name, value, path, age);
		} catch (Exception e) {
			logger.error("setCookie error", e);
		}
	}

	protected void addCookie(String name, String value, int age) {
		try {
			CookieUtils.addCookie(getRequest(), getResponse(), name, value, age);
		} catch (Exception e) {
			logger.error("setCookie error", e);
		}
	}

	protected void deleteCookie(String name) {
		CookieUtils.deleteCookieByName(getRequest(), getResponse(), name);
	}

	protected ServletRequestAttributes getServletRequsetAttributes() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
	}

	protected HttpServletRequest getRequest() {
		return getServletRequsetAttributes().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return getServletRequsetAttributes().getResponse();
	}

	public HttpSession getSession() {
		return getSession(true);
	}

	public HttpSession getSession(boolean create) {
		return getRequest().getSession(create);
	}

	protected void put(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	protected Object getAttribute(String name) {
		return getRequest().getAttribute(name);
	}

	protected Object[] getAll(String paramName) {
		return Params.getAll(paramName);
	}

	protected <T> T[] getAll(String paramName, Class<T> clazz) {
		return Params.getAll(paramName, clazz);
	}

	protected String get(String paramName) {
		return Params.get(paramName);
	}

	protected <T> T get(String name, Class<T> clazz) {
		return Params.get(name, clazz);
	}

	protected Boolean getBoolean(String name) {
		return Params.getBoolean(name);
	}

	protected boolean getBool(String name) {
		return Params.getBool(name);
	}

	protected java.sql.Date getDate(String name) {
		return Params.getDate(name);
	}

	protected Date getDateTime(String name) {
		return Params.getDateTime(name);
	}

	protected Float getFloat(String name) {
		return Params.getFloat(name);
	}

	protected Integer getInteger(String name) {
		return Params.getInteger(name);
	}

	protected Long getLong(String name) {
		return Params.getLong(name);
	}

	// populate------------------------------------------------------------------

	/**
	 * 将request中的参数设置到clazz对应的bean。
	 * 
	 * @param request
	 * @param clazz
	 * @param title
	 * @return
	 */
	protected <T> T populate(Class<T> clazz, String shortName) {
		return PopulateHelper.populate(clazz, shortName);
	}

	protected Object populate(String entityName) {
		return PopulateHelper.populate(entityName);
	}

	protected Object populate(Class<?> clazz) {
		return PopulateHelper.populate(clazz);
	}

	protected Object populate(String entityName, String shortName) {
		return PopulateHelper.populate(entityName, shortName);
	}

	protected Object populate(Object obj, String entityName, String shortName) {
		return PopulateHelper.populate(obj, entityName, shortName);
	}
	
	/**
	 * query string and form control
	 * 
	 * @return
	 */
	public String getParamstring() {
		StringWriter sw = new StringWriter();
		Enumeration<?> em = getRequest().getParameterNames();
		while (em.hasMoreElements()) {
			String attr = (String) em.nextElement();
			String value = getRequest().getParameter(attr);
			if (attr.equals("x-requested-with")) {
				continue;
			}
			sw.write(attr);
			sw.write('=');
				// StringEscapeUtils.escapeJavaScript(sw, value);
			sw.append(value);
			if (em.hasMoreElements()) {
				sw.write('&');
			}
		}
		return sw.toString();
	}

}
