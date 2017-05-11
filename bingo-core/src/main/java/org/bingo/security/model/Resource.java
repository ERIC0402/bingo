package org.bingo.security.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.bingo.common.collection.CollectUtils;
import org.bingo.hibernate.model.entity.LongIdObject;

@Entity(name = "org.bingo.security.model.Resource")
public class Resource extends LongIdObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String name;
	
	@Column(length = 100)
	private String path;
	
	private int scope;
	
	@OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
	private Set<Authority> authorities = CollectUtils.newHashSet();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public static class Scope {
		
		/** 不受保护的公共资源（匿名可访问） */
		public static final int PUBLIC = 0;
		
		/** 受保护的公有资源 （需要登录）*/
		public static final int PROTECTED = 1;
		
		/** 受保护的私有资源 （需对用户进行授权）*/
		public static final int PRIVATE = 2;
		
	}
	
}
