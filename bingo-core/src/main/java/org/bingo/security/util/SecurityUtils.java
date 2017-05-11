package org.bingo.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.bingo.security.exception.AuthenticationException;
import org.bingo.security.impl.UserToken;

public final class SecurityUtils {

	public static UserToken getPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null == auth) throw new AuthenticationException();
		UserToken user = (UserToken) auth.getPrincipal();
		if (null == user.getUsername()) throw new AuthenticationException();
		return user;
	}

	public static Long getUserId() {
		try {
			return getPrincipal().getId();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getUsername() {
		return getPrincipal().getUsername();
	}

}
