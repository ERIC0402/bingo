package org.bingo.security.service;

import org.bingo.security.model.User;

public interface UserService {
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public User get(Long id);
	
	/**
	 * 更具用户名获取用户
	 * @param loginName
	 * @return
	 */
	public User get(String userName);
	
}
