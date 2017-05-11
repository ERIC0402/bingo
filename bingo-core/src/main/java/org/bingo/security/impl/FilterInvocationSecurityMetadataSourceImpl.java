package org.bingo.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.bingo.security.model.Authority;
import org.bingo.security.model.Resource;
import org.bingo.security.service.ResourceService;

/**
 * 资源源数据定义，即定义某一资源可以被哪些权限访问
 *
 */
@Service("securityMetadataSource")
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LoggerFactory.getLogger(FilterInvocationSecurityMetadataSourceImpl.class);

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Autowired
	private ResourceService resourceService;

	private PathMatcher pathMatcher = new AntPathMatcher();

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/**
	 * 加载资源与权限的映射关系
	 * 
	 * @return
	 */
	private Map<String, Collection<ConfigAttribute>> loadResourceMatchAuthority() {
		Map<String, Collection<ConfigAttribute>> map = new HashMap<String, Collection<ConfigAttribute>>();
		List<Resource> resources = resourceService.findAllResource();
		if (resources != null && resources.size() > 0) {
			for (Resource resource : resources) {
				Collection<ConfigAttribute> authorities = new ArrayList<ConfigAttribute>();
				for (Authority authority : resource.getAuthorities()) {
					authorities.add(new SecurityConfig(authority.getRole().getName()));
				}
				map.put(resource.getPath(), authorities);
			}
		}
		return map;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		logger.info("requestUrl is " + url);
		if (resourceMap == null) {
			resourceMap = loadResourceMatchAuthority();
		}
		// 比较url是否存在
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (pathMatcher.match(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
