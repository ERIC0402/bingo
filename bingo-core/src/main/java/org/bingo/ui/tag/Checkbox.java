package org.bingo.ui.tag;

import org.bingo.ui.TagForm;


public class Checkbox extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	
    private Boolean checked;
    
    private Boolean readonly;
    
    @Override
	public void initValue() {
    	super.initValue();
    	checked = false;
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
