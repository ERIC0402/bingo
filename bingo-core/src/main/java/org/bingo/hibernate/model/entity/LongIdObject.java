package org.bingo.hibernate.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.bingo.hibernate.model.LongIdEntity;
import org.bingo.hibernate.predicates.ValidEntityKeyPredicate;

@MappedSuperclass
public class LongIdObject implements LongIdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "table_sequence")
	@GenericGenerator(name = "table_sequence", strategy = "org.bingo.hibernate.support.TableSeqGenerator")
	protected Long id;
	
	public LongIdObject() {
		super();
	}
	
	public LongIdObject(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getIdentifier() {
		return id;
	}

	@Override
	public boolean isPersisted() {
		return ValidEntityKeyPredicate.INSTANCE.evaluate(id);
	}

	@Override
	public boolean isTransient() {
		return !ValidEntityKeyPredicate.INSTANCE.evaluate(id);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-64900959, -454788261).append(this.id).toHashCode();
	}

	/**
	 * 比较id,如果任一方id是null,则不相等
	 * <p>
	 * 因为业务对象被CGlib或者javassist增强的原因，这里只提供一般的基于id的比较,不提供基于Class的比较。如果在存在继承结构， 请重置equals方法。
	 * 
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(final Object object) {
		if (!(object instanceof LongIdObject)) { return false; }
		LongIdObject rhs = (LongIdObject) object;
		if (null == getId() || null == rhs.getId()) { return false; }
		return getId().equals(rhs.getId());
	}
}
