package org.bingo.security.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.bingo.hibernate.model.entity.LongIdObject;

@Entity(name = "org.bingo.security.model.Authority")
public class Authority extends LongIdObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Role role;
	
	@ManyToOne
	private Resource resource;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
