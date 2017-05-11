package org.ssh.service;

import java.util.List;

import org.ssh.model.User;

/**
 *  userService接口
 */
public interface UserService {
	
	User load(Long id);

	User get(Long id);

	List<User> findAll();

	void persist(User entity);

	Long save(User entity);

	void saveOrUpdate(User entity);

	void delete(Long id);

	void flush();

}
