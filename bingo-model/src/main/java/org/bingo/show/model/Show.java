package org.bingo.show.model;

/**
 * 
 * 作者：王政
 * 创建时间：2016年9月20日 上午10:00:45
 */
public class Show {
	
	private Long id;
	
	private String name;
	
	private int age;
	
	private String level;
	
	private String desc;

	public Show(Long id, String name, int age, String level, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.level = level;
		this.desc = desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
