/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.bingo.springmvc.convention.Flash;

/**
 * ROR's flash
 * 
 */
public class FlashInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Flash flash = (Flash) request.getSession().getAttribute("flash");
		if (null != flash) flash.nextToNow();
		return true;
	}
	

}
