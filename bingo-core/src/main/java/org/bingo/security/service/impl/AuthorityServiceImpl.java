package org.bingo.security.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bingo.common.collection.CollectUtils;
import org.bingo.security.model.Authority;
import org.bingo.security.model.Role;
import org.bingo.security.model.User;
import org.bingo.security.service.AuthorityService;
import org.bingo.security.service.RoleService;
import org.bingo.springmvc.impl.BaseServiceImpl;

@Service("authorityService")
public class AuthorityServiceImpl extends BaseServiceImpl implements AuthorityService {
	
	@Autowired
	private RoleService roleService;
	
	public Set<Authority> getAuthorities(User user) {
		if (null == user) return Collections.emptySet();
		Set<Role> roles = roleService.getRoles(user);
		Set<Authority> authorities= CollectUtils.newHashSet();
		for (final Role role : roles) {
			authorities.addAll(role.getAuthorities());
		}
		return authorities;
	}
	
	public List<Authority> findAllAuthority() {
		return entityDao.getAll(Authority.class);
	}

}
