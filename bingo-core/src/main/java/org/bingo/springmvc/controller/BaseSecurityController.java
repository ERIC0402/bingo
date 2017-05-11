package org.bingo.springmvc.controller;

import org.bingo.security.util.SecurityUtils;

public class BaseSecurityController extends BaseDispatchController {
	
	protected String getUsername() {
		try {
			return SecurityUtils.getUsername();
		} catch (Exception e) {
		}
		return null;
	}

}
