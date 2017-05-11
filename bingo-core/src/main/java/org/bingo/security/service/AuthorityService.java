package org.bingo.security.service;

import java.util.List;
import java.util.Set;

import org.bingo.security.model.Authority;
import org.bingo.security.model.User;

public interface AuthorityService {
	
	/**
	 * 获取该用户所有权限
	 * @param user
	 * @return
	 */
	public Set<Authority> getAuthorities(User user);
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Authority> findAllAuthority();

}
