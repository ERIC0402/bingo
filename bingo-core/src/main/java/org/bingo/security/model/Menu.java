package org.bingo.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.bingo.hibernate.model.entity.LongIdObject;

@Entity(name = "org.bingo.security.model.Menu")
public class Menu extends LongIdObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String name;
	
	@Column(length = 100)
	private String entry;
	
	@ManyToMany
	private Set<Resource> resources = new HashSet<Resource>();
	
	@ManyToOne
	private Menu parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Menu> childrens = new HashSet<Menu>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public Set<Menu> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Menu> childrens) {
		this.childrens = childrens;
	}
	
}
