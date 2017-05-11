package org.bingo.springmvc.controller;

import java.net.URLEncoder;
import java.util.Locale;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.bingo.springmvc.convention.Flash;
import org.bingo.springmvc.convention.Message;


public class BaseDispatchController extends BaseController {
	
	protected String forward(String view) {
		return view;
	}
	
	protected String redirect(String method) {
		return redirect(method, null, null);
	}
	
	protected String redirect(String method, String message) {
		return redirect(method, new Message(message), null);
	}
	
	protected String redirect(String method, Message message) {
		return redirect(method, message, null);
	}
	
	protected String redirect(String method, Message message, String params) {
		String redirectParams = get("params");
		if(StringUtils.isNotBlank(redirectParams)) {
			if(params == null) {
				params = redirectParams;
			}else {
				params += "&" + redirectParams;
			}
		}
		if(message != null) {
			if(Message.ERROR.equals(message.getType())) {
				addFlashError(message.getMessage());
			}else {
				addFlashMessage(message.getMessage());
			}
		}
		return "redirect:" + method + buildParams(params);
	}
	
	public String buildParams(String paramStr) {
		StringBuilder buf = new StringBuilder();
		if (StringUtils.isNotEmpty(paramStr)) {
			String[] paramPairs = StringUtils.split(paramStr, "&");
			for (int i = 0; i < paramPairs.length; i++) {
				String key = StringUtils.substringBefore(paramPairs[i], "=");
				String value = StringUtils.substringAfter(paramPairs[i], "=");
				if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
					try {
						if (i == 0) {
							buf.append('?');
						} else {
							buf.append('&');
						}
						buf.append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
					} catch (Exception e) {
						throw new RuntimeException(e.getMessage());
					}
				}
			}
		}
		return buf.toString();
	}
	
	protected String getTextInternal(String msgKey, Object... args) {
		if (null == msgKey) return null;
		Locale locale = RequestContextUtils.getLocaleResolver(getRequest()).resolveLocale(getRequest());
		if (CharUtils.isAsciiAlpha(msgKey.charAt(0)) && msgKey.indexOf('.') > 0) {
			return ContextLoader.getCurrentWebApplicationContext().getMessage(msgKey, args, locale);
		} else {
			return msgKey;
		}
	}
	
	protected void addFlashError(String msgKey, Object... args) {
		getFlash().addError(getTextInternal(msgKey, args));
	}

	protected void addFlashMessage(String msgKey, Object... args) {
		getFlash().addMessage(getTextInternal(msgKey, args));
	}

	protected void addFlashMessageNow(String msgKey, Object... args) {
		getFlash().addMessageNow(getTextInternal(msgKey, args));
	}

	protected void addFlashErrorNow(String msgKey, Object... args) {
		getFlash().addErrorNow(getTextInternal(msgKey, args));
	}

	protected Flash getFlash() {
		Flash flash = (Flash) getSession().getAttribute("flash");
		if (null == flash) {
			flash = new Flash();
			getSession().setAttribute("flash", flash);
		}
		return flash;
	}

}
