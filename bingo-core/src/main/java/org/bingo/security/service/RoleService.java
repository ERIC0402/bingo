package org.bingo.security.service;

import java.util.Set;

import org.bingo.security.model.Role;
import org.bingo.security.model.User;

public interface RoleService {
	
	public Set<Role> getRoles(User user);

}
