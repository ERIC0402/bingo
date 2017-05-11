package org.bingo.ui.tag;

import org.bingo.ui.TagForm;


public class Radio extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	
    private Boolean readonly;
    
    @Override
	public void initValue() {
    	super.initValue();
    	readonly = false;
    	title = null;
	}
    
    @Override
    protected void evaluateParams() {
    	super.evaluateParams();
    }
    
	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
