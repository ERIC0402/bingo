package org.bingo.ui.tag;

import org.bingo.ui.TagRender;


public class Div extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String href;
    
    private Boolean isTarget;
    
    @Override
	public void initValue() {
    	super.initValue();
    	href = null;
    	isTarget = null;
    	tagClass = "";
	}
    
    @Override
    protected void evaluateParams() {
    	if(isTarget == null || isTarget) {
    		tagClass += " _ajax_target";
    	}
    }

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Boolean getIsTarget() {
		return isTarget;
	}

	public void setIsTarget(Boolean isTarget) {
		this.isTarget = isTarget;
	}
    
}
