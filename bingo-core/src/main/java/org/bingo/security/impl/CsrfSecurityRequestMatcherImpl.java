package org.bingo.security.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcherImpl implements RequestMatcher {

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	/**
	 * 需要验证的url列表
	 */
	private List<String> includeUrls;

	@Override
	public boolean matches(HttpServletRequest request) {
		if (includeUrls != null && includeUrls.size() > 0) {
			String servletPath = request.getServletPath();
			for (String url : includeUrls) {
				if (servletPath.contains(url)) {
					return !allowedMethods.matcher(request.getMethod()).matches();
				}
			}
		}
		return false;
	}

	public List<String> getIncludeUrls() {
		return includeUrls;
	}

	public void setIncludeUrls(List<String> includeUrls) {
		this.includeUrls = includeUrls;
	}

}
