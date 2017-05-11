package org.bingo.ui.tag;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.bingo.ui.TagRender;


public class Bar extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Grid table;
	
	@Override
	public void initValue() {
		super.initValue();
		table = null;
	}
	
	@Override
	public boolean renderStart(Writer out) {
		Tag parent = getParent();
		if(parent instanceof Grid) {
			table = (Grid) getParent();
		}
		return table !=null;
	}
	
    @Override
    public int doEndTag() throws JspException {
    	table.setGridbar(getBody());
    	initValue();
    	return EVAL_PAGE;
    }

}
