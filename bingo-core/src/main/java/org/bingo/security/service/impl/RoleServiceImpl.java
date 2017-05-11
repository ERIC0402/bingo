package org.bingo.security.service.impl;

import java.util.Collections;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.bingo.security.model.Role;
import org.bingo.security.model.User;
import org.bingo.security.service.RoleService;
import org.bingo.springmvc.impl.BaseServiceImpl;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{
	
	public Set<Role> getRoles(User user) {
		if(user != null) {
			return user.getRoles();
		}else {
			return Collections.emptySet();
		}
	}

}
