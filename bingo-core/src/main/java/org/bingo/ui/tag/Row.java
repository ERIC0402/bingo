package org.bingo.ui.tag;

import java.io.Writer;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.bingo.ui.TagRender;


public class Row extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Grid table;
	
	private Iterator<?> iterator;
	
	private Integer index;
	
	private Object curObj;
	
	@Override
	public void initValue() {
		super.initValue();
		table = null;
		iterator = null;
		index = 0;
		curObj = null;
	}
	
	@Override
	public boolean renderStart(Writer out) {
		Tag parent = getParent();
		if(parent instanceof Grid) {
			table = (Grid) getParent();
			if (table.getItems() instanceof Iterable) {
				iterator = ((Iterable<?>) table.getItems()).iterator();
				next();
			}
		}else {
			table = null;
			iterator = null;
		}
		return table != null;
	}
	
	@Override
	public boolean renderAfterBody(Writer out) {
		writeTemplate();
		return next();
	}
	
	@Override
	public int doEndTag() throws JspException {
		initValue();
		return EVAL_PAGE;
	}

	private boolean next() {
		if(iterator != null && iterator.hasNext()) {
			index++;
			curObj = iterator.next();
			pageContext.setAttribute(table.getVar(), curObj);
			pageContext.setAttribute(table.getVar() + "_index", index);
			return true;
		}else {
			pageContext.removeAttribute(table.getVar());
			pageContext.removeAttribute(table.getVar() + "_index");
			return false;
		}
	}

	public Grid getTable() {
		return table;
	}

	public void setTable(Grid table) {
		this.table = table;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Object getCurObj() {
		return curObj;
	}

	public void setCurObj(Object curObj) {
		this.curObj = curObj;
	}

}
