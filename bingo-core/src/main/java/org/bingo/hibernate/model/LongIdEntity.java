/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface LongIdEntity extends Entity<Long> {

	public Long getId();

	public void setId(Long id);
}
