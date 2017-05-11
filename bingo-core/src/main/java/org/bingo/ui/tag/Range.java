package org.bingo.ui.tag;

import org.apache.commons.lang3.StringUtils;
import org.bingo.ui.TagForm;


public class Range extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ids;

	private String start;

	private String end;

	private String format;
	
	private String minDate;
	
	private String maxDate;
	
	private Date[] dates;
	
    @Override
	public void initValue() {
    	super.initValue();
    	ids = null;
    	start = null;
    	end = null;
    	format = "date";
    	dates = null;
    	minDate = null;
    	maxDate = null;
	}
    
    @Override
    protected void evaluateParams() {
    	String[] nameArray = StringUtils.split(getName(), ',');
		String[] idArray =  StringUtils.split(ids, ',');
		dates = new Date[nameArray.length];
		String format2 = Date.ResvervedFormats.get(format);
		if (null != format2) format = format2;
		String[] requiredArray = StringUtils.split(getRequired(), ',');
		String[] commentArray = StringUtils.split(getComment(), ',');
		String[] labelArray = StringUtils.split(getLabel(), ',');
		Form f = findParentTag(Form.class);
		for (int i = 0; i < nameArray.length; i++) {
			if (i >= 2) break;
			dates[i] = new Date();
			if(idArray != null){
				dates[i].setId(idArray[i]);
			}
			String name = nameArray[i];
			dates[i].setName(name);
			dates[i].setFormat(format);
			if (requiredArray != null) {
				dates[i].setRequired(requiredArray.length == 1 ? getRequired() : requiredArray[i]);
			}
			if (commentArray != null) {
				dates[i].setComment(commentArray.length == 1 ? getComment() : commentArray[i]);
			}
			if (labelArray != null) {
				dates[i].setLabel(labelArray.length == 1 ? getLabel() : labelArray[i]);
			}
			if (i == 0) {
				dates[0].setValue(start);
			}else {
				dates[1].setValue(end);
			}
	    	if(f != null) {
	    		f.addCheck(dates[i].getId(), ".date()");
	    		if ("true".equals(dates[i].getRequired()))
					f.addCheck(dates[i].getId(), ".required()");
	    	}
		}
		if(getRequired() != null && getRequired().indexOf("true") > 0) {
			setRequired("true");
		}
    }

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
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
