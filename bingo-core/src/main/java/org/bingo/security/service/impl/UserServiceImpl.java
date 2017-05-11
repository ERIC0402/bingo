package org.bingo.security.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.bingo.hibernate.query.builder.OqlBuilder;
import org.bingo.security.model.User;
import org.bingo.security.service.UserService;
import org.bingo.springmvc.impl.BaseServiceImpl;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	public User get(Long id) {
		return entityDao.get(User.class, id);
	}

	@Cacheable(value="dd")
	public User get(String userName) {
		if (StringUtils.isEmpty(userName)) {
			return null;
		}
		OqlBuilder<User> entityQuery = OqlBuilder.from(User.class, "user");
		entityQuery.where("user.username=:username", userName);
		List<User> users = entityDao.search(entityQuery);
		return (users.isEmpty()) ? null : users.get(0);
	}

}
