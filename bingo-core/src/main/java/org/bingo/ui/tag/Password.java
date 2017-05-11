package org.bingo.ui.tag;

import org.apache.commons.lang3.StringUtils;
import org.bingo.ui.TagForm;


public class Password extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String maxlength;
    
    private String minlength;
    
    @Override
	public void initValue() {
    	super.initValue();
    	maxlength = "20";
    	minlength = "6";
	}
    
    @Override
    protected void evaluateParams() {
    	super.evaluateParams();
    	Form f = findParentTag(Form.class);
    	if(f != null) {
    		if (StringUtils.isNotBlank(maxlength)) {
    			f.addCheck(getId(), ".maxl(" + maxlength + ")");
    		}
    		if (StringUtils.isNotBlank(minlength)) {
    			f.addCheck(getId(), ".minl(" + minlength + ")");
    		}
    	}
    }
    
	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getMinlength() {
		return minlength;
	}

	public void setMinlength(String minlength) {
		this.minlength = minlength;
	}

}
