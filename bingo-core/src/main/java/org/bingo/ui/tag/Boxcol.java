package org.bingo.ui.tag;

import java.io.Writer;

import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang3.StringUtils;

public class Boxcol extends Col {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	private String name;
	
	private Boolean checked;
	
	@Override
	public void initValue() {
		super.initValue();
		property = "id";
		type = "checkbox";
		name = null;
		checked = false;
		sortable = "false";
	}
	
	@Override
	public boolean renderStart(Writer out) {
		Tag parent = getParent();
		if(parent instanceof Row) {
			row = (Row) parent;
		}
		if(row != null && row.getCurObj() != null) {
			if(StringUtils.isBlank(name)) {
	    		name = row.getTable().getVar() + "." + property;
	    	}
		}
		return super.renderStart(out);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
