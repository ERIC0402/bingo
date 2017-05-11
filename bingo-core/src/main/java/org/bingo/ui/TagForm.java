package org.bingo.ui;

import org.bingo.ui.tag.Form;


public class TagForm extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String label;
	
	private String required;
	
	private String check;

	private String name;
    
	private String value;
    
	private String comment;
    
    @Override
	public void initValue() {
    	super.initValue();
    	label = null;
    	required = null;
    	check = null;
    	name = null;
    	value = null;
    	comment = null;
	}
    
    @Override
    protected void evaluateParams() {
    	super.evaluateParams();
    	Form f = findParentTag(Form.class);
    	if(f != null) {
    		if ("true".equals(required))
				f.addCheck(getId(), ".required()");
			if (null != check)
				f.addCheck(getId(), "." + check);
    	}
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

}
