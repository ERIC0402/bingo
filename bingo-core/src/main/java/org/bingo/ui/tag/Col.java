package org.bingo.ui.tag;

import java.io.Writer;
import java.lang.reflect.Method;

import javax.servlet.jsp.tagext.Tag;

import org.bingo.ui.TagRender;

public class Col extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String property;

	protected String title;

	protected String width;

	protected Row row;

	protected String sortable;
	
	protected Boolean isHidden;
	
	@Override
	public void initValue() {
		super.initValue();
		property = null;
		title = null;
		width = null;
		row = null;
		sortable = null;
		isHidden = false;
	}

	@Override
	public boolean renderStart(Writer out) {
		Tag parent = getParent();
		if(parent instanceof Row) {
			row = (Row) parent;
		}
		if(row != null) {
			if (row.getIndex().equals(0) || row.getIndex().equals(1)) {
				try {
					row.getTable().addCol((Col) this.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return super.renderStart(out);
	}

	public Object getValue() {
		return getFieldValueByName(row.getCurObj(), property);
	}

	/**
	* 使用反射根据属性名称获取属性值 
	* 
	* @param  fieldName 属性名称
	* @param  o 操作对象
	* @return Object 属性值
	*/
	private Object getFieldValueByName(Object o, String fieldName) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return null;
		}
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

}
