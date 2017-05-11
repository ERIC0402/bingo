package org.bingo.ui.tag;

import java.util.Map;

import org.bingo.common.collection.CollectUtils;
import org.bingo.ui.TagForm;


public class Date extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Map<String, String> ResvervedFormats = CollectUtils.toMap(new String[] { "date",
	"yyyy-MM-dd" }, new String[] { "datetime", "yyyy-MM-dd HH:mm" });

	private String format;
	
	protected String minDate;
	
	protected String maxDate;
    
    @Override
	public void initValue() {
    	super.initValue();
    	format = "date";
    	minDate = null;
    	maxDate = null;
	}
    
    @Override
    protected void evaluateParams() {
    	super.evaluateParams();
    	Form f = findParentTag(Form.class);
    	if(f != null) {
    		f.addCheck(getId(), ".date()");
    	}
    	String format2 = ResvervedFormats.get(format);
		if (null != format2) format = format2;
    }

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
    
}
