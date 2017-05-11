package org.bingo.ui.tag;

import org.apache.commons.lang3.StringUtils;
import org.bingo.ui.TagForm;


public class Textfield extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String maxlength;
    
    private String format;
    
    private Boolean readonly;
    
    @Override
	public void initValue() {
    	super.initValue();
    	maxlength = "100";
    	format = null;
    	readonly = false;
	}
    
    @Override
    protected void evaluateParams() {
    	super.evaluateParams();
    	Form f = findParentTag(Form.class);
    	if(f != null) {
    		if (StringUtils.isNotBlank(maxlength)) {
    			f.addCheck(getId(), ".maxl(" + maxlength + ")");
    		}
    	}
    }
    
	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

}
