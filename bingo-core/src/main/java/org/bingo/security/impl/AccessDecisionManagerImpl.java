package org.bingo.security.impl;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Service;

/**
 * 访问决策管理:通过资源所拥有的权限，对用户所具备的角色对应的权限进行裁定，决策是否用户具备访问资源的权限
 *
 */
@Service("accessDecisionManager")
public class AccessDecisionManagerImpl implements AccessDecisionManager {
	
	private MessageSourceAccessor messageSource = SpringSecurityMessageSource.getAccessor(); 

	/**
	 * 裁定当前用户对应权限authentication是否包含所请求资源所拥有的权限 如果成立 则通过裁定 否则发生异常
	 */
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

		if (configAttributes == null) {
			return;
		}

		// 所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();

		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();

			// 访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();

			System.out.println("needPermission is " + needPermission);

			// 用户所拥有的权限authentication
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}

		// 没有权限
		throw new AccessDeniedException(messageSource.getMessage("AbstractAccessDecisionManager.accessDenied"));

	}

	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
