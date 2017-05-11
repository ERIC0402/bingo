package org.bingo.ui.tag;

import org.bingo.ui.TagRender;


public class Toolbar extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String title;
    
    private Boolean dynamicTitle;
    
    @Override
	public void initValue() {
    	super.initValue();
    	title = null;
    	dynamicTitle = true;
	}
    
    @Override
    protected void evaluateParams() {
    	if (null != title && dynamicTitle) {
    		Object id  = (Object) parameter("entityId");
			if(id == null){
				title = "新建&nbsp;" + title;
			}else{
				title = "修改&nbsp;" + title;
			}
		}
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getDynamicTitle() {
		return dynamicTitle;
	}

	public void setDynamicTitle(Boolean dynamicTitle) {
		this.dynamicTitle = dynamicTitle;
	}

}
