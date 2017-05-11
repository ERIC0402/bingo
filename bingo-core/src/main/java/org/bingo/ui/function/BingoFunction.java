package org.bingo.ui.function;

import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class BingoFunction {

	public static String getParamstring(HttpServletRequest request) {
		request.getHeaderNames();
		StringWriter sw = new StringWriter();
		Enumeration<?> em = request.getParameterNames();
		while (em.hasMoreElements()) {
			String attr = (String) em.nextElement();
			String value = request.getParameter(attr);
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
	
	public static String transformRequestURI(HttpServletRequest request) {
		return StringUtils.substringBetween(request.getRequestURI(), "/views", ".jsp");
	}
	
}
