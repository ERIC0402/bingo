package org.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ssh.dao.UserDao;
import org.ssh.model.User;

/**
 * 用户Dao实现类
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public User load(Long id) {
		return (User) this.getCurrentSession().load(User.class, id);
	}
	
	@Override
	public User get(Long id) {
		return (User) this.getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		List<User> Users = this.getCurrentSession().createQuery("from User").setCacheable(true).list();
		return Users;
	}

	@Override
	public void persist(User entity) {
		this.getCurrentSession().persist(entity);

	}

	@Override
	public Long save(User entity) {
		return (Long) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(User entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Long id) {
		User entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();

	}

}
