package org.bingo.ui.tag;

import java.util.Map;

import org.bingo.common.collection.CollectUtils;
import org.bingo.ui.TagRender;


/**
 */
public class Messages extends TagRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;

    private String action;
    
    private String method;
    
    private String target;
    
    private String onsubmit;
    
    private Map<String, StringBuilder> checks = CollectUtils.newHashMap();
    
    @Override
	public void initValue() {
    	super.initValue();
    	title = null;
    	action = null;
    	method = "post";
    	target = null;
    	onsubmit = null;
    	checks = CollectUtils.newHashMap();
	}
    
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOnsubmit() {
		return onsubmit;
	}

	public void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}
	
	public void addCheck(String id, String check) {
		StringBuilder sb = checks.get(id);
		if (null == sb) {
			sb = new StringBuilder(20);
			checks.put(id, sb);
		}
		if (!check.startsWith(".")) {
			check = "." + check;
		}
		sb.append(check);
	}

	public Map<String, StringBuilder> getChecks() {
		return checks;
	}

	public void setChecks(Map<String, StringBuilder> checks) {
		this.checks = checks;
	}

}
