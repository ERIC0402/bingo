package org.bingo.security.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.bingo.hibernate.model.TemporalActiveEntity;
import org.bingo.hibernate.model.entity.LongIdObject;
import org.bingo.hibernate.util.EntityUtils;

@Entity(name = "org.bingo.security.model.User")
public class User extends LongIdObject implements TemporalActiveEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	@Column(length = 100)
	private String username;
	
	/**
	 * 密码
	 */
	@Column(length = 100)
	private String password;
	
	/**
	 * 全名
	 */
	@Column(length = 100)
	private String fullName;
	
	/**
	 * 账户生效时间
	 */
	protected Date effectiveAt;

	/**
	 * 账户失效时间
	 */
	protected Date invalidAt;

	/**
	 * 密码失效时间
	 */
	protected Date passwordExpiredAt;

	/**
	 * 备注
	 */
	protected String remark;
	
	/**
	 * 状态
	 */
	protected Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<Role>();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Date getEffectiveAt() {
		return effectiveAt;
	}

	@Override
	public void setEffectiveAt(Date effectiveAt) {
		this.effectiveAt = effectiveAt;
	}

	@Override
	public Date getInvalidAt() {
		return invalidAt;
	}

	@Override
	public void setInvalidAt(Date invalidAt) {
		this.invalidAt = invalidAt;
	}
	
	/**
	 * 是否账户过期
	 */
	public boolean isAccountExpired() {
		return EntityUtils.isExpired(this);
	}

	/**
	 * 是否密码过期
	 */
	public boolean isPasswordExpired() {
		return (null != passwordExpiredAt && new Date().after(passwordExpiredAt));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getPasswordExpiredAt() {
		return passwordExpiredAt;
	}

	public void setPasswordExpiredAt(Date passwordExpiredAt) {
		this.passwordExpiredAt = passwordExpiredAt;
	}
	
}
