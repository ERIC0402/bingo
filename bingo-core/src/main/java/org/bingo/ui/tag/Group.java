package org.bingo.ui.tag;

import org.bingo.ui.TagRender;


public class Group extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String title;
    
    @Override
	public void initValue() {
    	super.initValue();
    	title = null;
	}
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
