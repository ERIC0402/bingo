package org.bingo.ui.tag;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bingo.common.collection.CollectUtils;
import org.bingo.ui.TagForm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;


public class Radios extends TagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object items;

	private Radio[] radios;

	private String keyName;
	
	private String valueName;
    
    @Override
	public void initValue() {
    	super.initValue();
    	items = null;
    	radios = null;
    	keyName = "id";
    	valueName = "title";
	}
    
    @Override
    protected void evaluateParams() {
    	super.evaluateParams();
		if(getLabel() == null) setLabel(getName());

		List<?> keys = convertItems();
		radios = new Radio[keys.size()];
		int i = 0;
		for (Object key : keys) {
			radios[i] = new Radio();
			radios[i].setTitle(String.valueOf(((Map<?,?>)items).get(key)));
			radios[i].setValue(key.toString());
			i++;
		}
		if (null == this.getValue() && radios.length > 0) this.setValue(radios[0].getValue());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private List<?> convertItems() {
		if (items instanceof Map) return CollectUtils.newArrayList(((Map<Object, Object>) items).keySet());
		Map<Object, Object> defaultItemMap = new TreeMap<Object, Object>();
		defaultItemMap.put("1", "是");
		defaultItemMap.put("0", "否");
		List<?> defaultKeys = CollectUtils.newArrayList("1", "0");
		Map<Object, Object> itemMap = new TreeMap<Object, Object>();
		List keys = CollectUtils.newArrayList();
		if (null == items) {
			keys = defaultKeys;
			items = defaultItemMap;
		} else if (items instanceof String) {
			if (StringUtils.isBlank((String) items)) {
				keys = defaultKeys;
				items = defaultItemMap;
			}else{
				JSONObject jsonObject = JSON.parseObject(items.toString(), Feature.OrderedField);
				for (String key : jsonObject.keySet()) {
					keys.add(key);
					itemMap.put(key, jsonObject.get(key));
				}
				items = itemMap;
			}
		} else if(items instanceof Collection<?>){
			for(Object o :  (Collection) items){
				try {
					Object key = BeanUtils.getProperty(o, keyName);
					Object value = BeanUtils.getProperty(o, valueName);
					keys.add(key);
					itemMap.put(key, value);
				} catch (SecurityException e) {
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
				} catch (NoSuchMethodException e) {
				}
			}
			items = itemMap;
		}
		return keys;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public Radio[] getRadios() {
		return radios;
	}

	public void setRadios(Radio[] radios) {
		this.radios = radios;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
    
}
