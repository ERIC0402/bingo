package org.ssh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户信息类
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 性别（0 男 1女）
	 */
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}