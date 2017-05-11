/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.model;

import org.bingo.hibernate.model.query.page.PageLimit;

public interface LimitQuery<T> extends Query<T> {

	public PageLimit getLimit();

	public LimitQuery<T> limit(final PageLimit limit);

	public Query<T> getCountQuery();
}
