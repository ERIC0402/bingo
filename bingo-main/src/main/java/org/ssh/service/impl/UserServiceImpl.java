package org.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssh.dao.UserDao;
import org.ssh.model.User;
import org.ssh.service.UserService;

/**
 * UserService 的实现
 */
@Service("userService1")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User load(Long id) {
		return userDao.load(id);
	}

	@Override
	public User get(Long id) {
		return userDao.get(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void persist(User entity) {
		userDao.persist(entity);
	}

	@Override
	public Long save(User entity) {
		return userDao.save(entity);
	}

	@Override
	public void saveOrUpdate(User entity) {
		userDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public void flush() {
		userDao.flush();
	}

}
