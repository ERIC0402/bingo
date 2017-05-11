package org.bingo.ui.tag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bingo.common.collection.CollectUtils;
import org.bingo.ui.TagForm;


public class Checkboxs extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object items;

	private Checkbox[] checkboxes;

	private Object value;

	private Object min;

	private Object max;

	private String keyName;
	
	private String valueName;
    
    @Override
	public void initValue() {
    	super.initValue();
    	items = null;
    	checkboxes = null;
    	value = null;
    	min = null;
    	max = null;
    	keyName = "id";
    	valueName = "name";
	}
    
    @Override
    protected void evaluateParams() {
    	super.evaluateParams();
		List<?> keys = convertItems();
		List<?> values = convertValue();
		checkboxes = new Checkbox[keys.size()];
		int i = 0;
		Form myform = findParentTag(Form.class);
		Integer minValue = getValidateNum(min);
		Integer maxValue = getValidateNum(max);
		if (null != myform) {
			if ("true".equals(getRequired())) {
				myform.addCheck(id, "assert($(\"input[name='" + getName() + "']:checked\").length != 0,'必须勾选一项')");
			}
		}
		if (minValue != null && maxValue != null) {
			if (minValue > maxValue) {
				Integer temp = maxValue;
				maxValue = minValue;
				minValue = temp;
			}
		}
		if (minValue != null && minValue > 0 && minValue <= checkboxes.length) {
			myform.addCheck(id, "assert($(\"input[name='" + getName() + "']:checked\").length >= " + minValue
					+ ",'至少勾选" + minValue + "项')");
		}
		if (maxValue != null && maxValue < checkboxes.length && maxValue > 0) {
			myform.addCheck(id, "assert($(\"input[name='" + getName() + "']:checked\").length <= " + maxValue
					+ ",'最多勾选" + maxValue + "项')");
		}
		for (Object key : keys) {
			checkboxes[i] = new Checkbox();
			checkboxes[i].setTitle(String.valueOf(((Map<?, ?>) items).get(key)));
			checkboxes[i].setValue(key.toString());
			if (CollectionUtils.isNotEmpty(values) && values.contains(key)) {
				checkboxes[i].setChecked(true);
			}
			i++;
		}
    }
    
    @SuppressWarnings("unchecked")
	private List<?> convertItems() {
		if (items instanceof Map) return CollectUtils.newArrayList(((Map<Object, Object>) items).keySet());
		Map<Object, Object> itemMap = new TreeMap<Object, Object>();
		List<Object> keys = CollectUtils.newArrayList();
		if (items instanceof String) {
			if (StringUtils.isBlank((String) items)) {
				return CollectUtils.newArrayList();
			} else {
				String[] titleArray = StringUtils.split(items.toString(), ',');
				for (int i = 0; i < titleArray.length; i++) {
					String titleValue = titleArray[i];
					int semiconIndex = titleValue.indexOf(':');
					if (-1 != semiconIndex) {
						keys.add(titleValue.substring(0, semiconIndex));
						itemMap.put(titleValue.substring(0, semiconIndex),
								titleValue.substring(semiconIndex + 1));
					}
				}
			}
		} else if (items instanceof List) {
			for (Object object : (List<?>) items) {
				try {
					if(object instanceof String || object instanceof Number){
						keys.add(object);
						itemMap.put(object, object);
					}else{
						Object value = PropertyUtils.getProperty(object, keyName);
						Object title = PropertyUtils.getProperty(object, valueName);
						keys.add(value);
						itemMap.put(value, title);
					}
				} catch (Exception e) {
					return CollectUtils.newArrayList();
				}
			}
		}
		items = itemMap;
		return keys;
	}

	private Integer getValidateNum(Object number) {
		try {
			return Integer.parseInt(String.valueOf(number));
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<?> convertValue() {
		List<Object> values = CollectUtils.newArrayList();
		if (value instanceof List) {
			try {
				for (Object object : (List<Object>) value) {
					if(object instanceof String || object instanceof Number){
						values.add(object);
					}else{
						values.add(PropertyUtils.getProperty(object, keyName));
					}
				}
				return values;
			} catch (Exception e) {
				return CollectUtils.newArrayList();
			}
		} else if (value instanceof Object[]) {
			if (value != null && ((Object[]) value).length > 0) { 
				return Arrays.asList((Object[]) value); 
			}else {
				return CollectUtils.newArrayList();
			}
		} else {
			String valueStr = String.valueOf(value);
			if (StringUtils.isNotBlank(valueStr)) {
				return Arrays.asList(valueStr.split(","));
			} else {
				return CollectUtils.newArrayList();
			}
		}
	}
    
}
