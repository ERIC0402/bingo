/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.predicates;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;

/**
 * 判断该对象是否字符串且不是空字符串
 * 作者：王政
 * 创建时间：2016年3月3日 上午10:42:59
 */
public class NotEmptyStringPredicate implements Predicate {
	
	public static final NotEmptyStringPredicate INSTANCE = new NotEmptyStringPredicate();

	/**
	 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
	 */
	public boolean evaluate(final Object value) {
		return (null != value) && (value instanceof String) && StringUtils.isNotEmpty((String) value);
	}

}
