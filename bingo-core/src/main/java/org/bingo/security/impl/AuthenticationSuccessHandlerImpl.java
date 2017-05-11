package org.bingo.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.bingo.security.filter.UsernamePasswordCaptchaAuthenticationFilter;

public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		request.getSession(false).removeAttribute(UsernamePasswordCaptchaAuthenticationFilter.LOGIN_FAIL_SIZE);
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
