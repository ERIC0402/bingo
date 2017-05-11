package org.bingo.ui.tag;

import org.bingo.ui.TagRender;


/**
 * 提交
 * 作者：王政
 * 创建时间：2016年7月15日 下午3:17:57
 */
public class Submit extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String formId;
	
	private String onsubmit;
	
	private String action;
	
	private String value;
	
	private String target;
	
	@Override
	public void initValue() {
		super.initValue();
		formId = null;
		onsubmit = null;
		action = null;
		value = null;
		target = null;
	}
	
	@Override
	protected void evaluateParams() {
		if (null == formId) {
			TagRender tag = findParentTag(Form.class);
			if(tag instanceof Form) {
				Form f = (Form) tag;
				formId = f.getId();
			}
		}
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getOnsubmit() {
		return onsubmit;
	}

	public void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
