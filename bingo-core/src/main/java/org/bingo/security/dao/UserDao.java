package org.bingo.security.dao;

import org.bingo.security.model.User;

public interface UserDao {
	
	User getUser(String userName);
	
}
