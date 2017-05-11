package org.bingo.ui.tag;

import org.bingo.ui.TagRender;


/**
 * 重置
 * 作者：王政
 * 创建时间：2016年7月15日 下午3:17:57
 */
public class Reset extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value;
	
	@Override
	public void initValue() {
		super.initValue();
		value = null;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
