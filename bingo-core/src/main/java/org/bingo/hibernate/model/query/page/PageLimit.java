/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.model.query.page;

/**
 * 查询分页限制
 * 
 * @author chaostone
 */
public class PageLimit implements Limit {

	private int pageNo;

	private int pageSize;

	public PageLimit() {
		super();
	}

	public PageLimit(final int pageNo, final int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
	}

	public boolean isValid() {
		return pageNo > 0 && pageSize > 0;
	}

}
