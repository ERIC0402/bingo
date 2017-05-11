package org.bingo.security.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.bingo.common.collection.CollectUtils;
import org.bingo.security.model.Authority;
import org.bingo.security.service.AuthorityService;
import org.bingo.security.service.UserService;

/**
 * 自定义用户与权限的关系
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	protected static Logger logger = LoggerFactory.getLogger("service");
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor(); 
	
	/**
	 * 根据用户名获取用户-权限等用户信息
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetails user = null;
		try {
			org.bingo.security.model.User dbUser = userService.get(username);
			user = new UserToken(dbUser.getId(), dbUser.getUsername(), dbUser.getFullName(), dbUser.getPassword().toLowerCase(), dbUser.getEnabled(), dbUser.isAccountExpired(), dbUser.isPasswordExpired(), false, getAuthorities(dbUser));
		} catch (Exception ee) {
			ee.printStackTrace();
			logger.error(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{username}));
			throw new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{username}));
		}
		return user;
	}

	/** 
	* 获得访问用户的所有权限 
	*  
	* @param access 
	* @return 
	*/
	private Collection<GrantedAuthority> getAuthorities(org.bingo.security.model.User dbUser) {
		List<GrantedAuthority> authList = CollectUtils.newArrayList();
		Set<Authority> authorities = authorityService.getAuthorities(dbUser);
		if(authorities != null && authorities.size() > 0) {
			for(Authority authority : authorities) {
				authList.add(new SimpleGrantedAuthority(authority.getRole().getName()));
			}
		}
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authList;
	}

}
