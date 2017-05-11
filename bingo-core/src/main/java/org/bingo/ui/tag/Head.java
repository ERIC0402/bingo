package org.bingo.ui.tag;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.bingo.ui.TagRender;


/**
 * 页头
 * 作者：王政
 * 创建时间：2016年7月15日 下午3:17:57
 */
public class Head extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String title;
    
    private Boolean isAjax;
    
    @Override
	public void initValue() {
    	super.initValue();
    	title = null;
    	isAjax = false;
	}
    
    @Override
    protected void evaluateParams() {
    	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    	if(StringUtils.isNotBlank(request.getHeader("x-requested-with"))) {
    		isAjax = true;
    	}else {
    		isAjax = false;
    	}
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsAjax() {
		return isAjax;
	}

	public void setIsAjax(Boolean isAjax) {
		this.isAjax = isAjax;
	}

}
