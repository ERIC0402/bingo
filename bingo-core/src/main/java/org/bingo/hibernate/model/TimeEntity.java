/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.model;

import java.io.Serializable;
import java.util.Date;

public interface TimeEntity<T extends Serializable> extends Entity<T> {

	public Date getCreatedAt();

	public void setCreatedAt(Date createdAt);

	public Date getUpdatedAt();

	public void setUpdatedAt(Date updatedAt);
}
