package org.bingo.ui.tag;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bingo.common.collection.CollectUtils;
import org.bingo.hibernate.model.query.page.Page;
import org.bingo.springmvc.convention.Flash;
import org.bingo.ui.TagRender;

public class Grid extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Col> cols;

	private Object items;

	private String var;

	private String gridbar;

	private String sortable;

	private String emptyMsg;
	
	private List<String> actionMessages;
	
	private List<String> actionErrors;

	@Override
	public void initValue() {
		super.initValue();
		cols = CollectUtils.newArrayList();
		items = null;
		var = null;
		gridbar = null;
		sortable = "true";
		emptyMsg = null;
		actionMessages = null;
		actionErrors = null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void evaluateParams() {
		Flash flash = (Flash) pageContext.getSession().getAttribute("flash");
		if(flash != null) {
			actionMessages = (List<String>) flash.now.get(Flash.MESSAGES);
			actionErrors = (List<String>) flash.now.get(Flash.ERRORS);
		}
		super.evaluateParams();
	}

	public boolean getHasbar() {
		return StringUtils.isNotBlank(gridbar);
	}

	public boolean isPageable() {
		return items instanceof Page<?>;
	}

	public boolean isNotFullPage() {
		if (isPageable()) {
			return ((Page<?>) items).size() < ((Page<?>) items).getPageSize();
		} else {
			return ((List<?>) items).size() < Page.DEFAULT_PAGE_SIZE;
		}
	}

	public boolean isSortable(Col cln) {
		return ("true".equals(sortable) && !ObjectUtils.equals(cln.getSortable(), "false") && null != cln.getProperty());
	}

	public List<Col> getCols() {
		return cols;
	}

	public void addCol(Col col) {
		cols.add(col);
	}

	public Object getItems() {
		return items;
	}

	public int getPageNo() {
		return ((Page<?>) items).getPageNo();
	}

	public int getPageSize() {
		return ((Page<?>) items).getPageSize();
	}

	public int getTotal() {
		return ((Page<?>) items).getTotal();
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getGridbar() {
		return gridbar;
	}

	public void setGridbar(String gridbar) {
		this.gridbar = gridbar;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public String getEmptyMsg() {
		return emptyMsg;
	}

	public void setEmptyMsg(String emptyMsg) {
		this.emptyMsg = emptyMsg;
	}
	
	public boolean hasActionErrors() {
		return actionErrors != null && !actionErrors.isEmpty();
	}

	public boolean hasActionMessages() {
		return actionMessages != null && !actionMessages.isEmpty();
	}

	public Collection<String> getActionMessages() {
		return actionMessages;
	}

	public Collection<String> getActionErrors() {
		return actionErrors;
	}

}
