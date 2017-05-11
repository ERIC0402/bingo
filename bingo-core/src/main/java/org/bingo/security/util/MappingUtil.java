package org.bingo.security.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.bingo.common.collection.CollectUtils;

public class MappingUtil {
	
	@Autowired
    private RequestMappingHandlerMapping handlerMapping;
	
	public static Map<String, String> mappingMap = CollectUtils.newHashMap();
	
	public void init() {
		Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            mappingMap.put(method.getMethod().getDeclaringClass().getName() + "." + method.getMethod().getName(), info.getPatternsCondition().toString());
        }
	}

}
