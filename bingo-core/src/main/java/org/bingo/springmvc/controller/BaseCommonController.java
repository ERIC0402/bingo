package org.bingo.springmvc.controller;

import org.bingo.security.model.User;
import org.bingo.security.util.SecurityUtils;

public class BaseCommonController extends BaseRouteController {
	
	/**
	 * 获取当前登入用户
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		Long userId = null;
		try {
			userId = getUserId();
		} catch (Exception e) {
		}
		if (userId != null) {
			return entityDao.get(User.class, userId);
		}
		return null;
	}
	
	protected Long getUserId() {
		try {
			Long userid = SecurityUtils.getUserId();
			return userid;
		} catch (Exception e) {
		}
		return null;
	}

	protected String getUsername() {
		try {
			return SecurityUtils.getUsername();
		} catch (Exception e) {
		}
		return null;
	}
	
}
