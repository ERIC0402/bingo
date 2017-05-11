package org.bingo.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.bingo.security.filter.UsernamePasswordCaptchaAuthenticationFilter;

public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		Integer loginFailSize = (Integer) session.getAttribute(UsernamePasswordCaptchaAuthenticationFilter.LOGIN_FAIL_SIZE);
		if (loginFailSize == null) {
			loginFailSize = 0;
		}
		session.setAttribute(UsernamePasswordCaptchaAuthenticationFilter.LOGIN_FAIL_SIZE, ++loginFailSize);
		super.onAuthenticationFailure(request, response, exception);
	}

}
