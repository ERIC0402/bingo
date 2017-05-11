package org.bingo.ui;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.bingo.ui.function.BingoFunction;
import org.bingo.ui.template.TemplateEngine;

/**
 * <p>
 * 所有控件渲染的基类。
 * </p>
 */
public class TagRender extends BodyTagSupport implements Cloneable {
	
	protected static final long serialVersionUID = 1L;

    public TagRender() {
		super();
		initValue();
	}

	/**
	 * 项目根路径
	 */
    protected String base;
	
    protected String tagClass;
	
	/**
	 * 皮肤
	 */
	protected String theme;
	
	/**
	 * 子标签内容
	 */
	protected Writer body;
	
    /**
     * 渲染控件start部分
     *
     * @param out
     * @see javax.servlet.jsp.tagext.TagSupport doStartTag
     */
    public boolean renderStart(Writer out) {
    	return true;
    }
    
    public boolean renderAfterBody(Writer out) {
    	return false;
    }

    /**
     * 渲染控件end部分
     *
     * @param out
     * @see javax.servlet.jsp.tagext.TagSupport doStartEnd
     */
    public boolean renderEnd(Writer out) throws JspException {
    	return true;
    }

    protected void mergeTemplate(Writer out) throws Exception {
    	TemplateEngine engine = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext()).getBean(TemplateEngine.class);
		engine.render(getTagTheme().getTemplatePath(getClass(), engine.getSuffix()), out, this);
	}
    
    protected void evaluateParams() {
    	
	}
    
    public void initValue() {
    	id = null;
    	base = null;
    	body = new StringWriter();
    	theme = null;
    	tagClass = null;
    }

    @Override
    public int doStartTag() throws JspException {
    	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    	base = request.getContextPath();
    	getTagTheme().setUibase(base);
        JspWriter out = this.pageContext.getOut();
        if (renderStart(out)) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }
    
    @Override
    public int doAfterBody() throws JspException {
    	if(null != getBodyContent()) {
    		try {
				body.write(getBodyContent().getString());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	JspWriter out = this.pageContext.getOut();
    	if(renderAfterBody(out)) {
    		body = new StringWriter();
    		return EVAL_BODY_AGAIN;
    	}else {
    		return SKIP_BODY;
    	}
    }


    @Override
    public int doEndTag() throws JspException {
		if (renderEnd(this.pageContext.getOut())) {
        	evaluateParams();
        	writeTemplate();
        	initValue();
            return EVAL_PAGE;
        }else {
        	initValue();
        	return SKIP_PAGE;
        }
    }
    
    @Override
    public void release() {
    	initValue();
    	super.release();
    }

    public void writeTemplate() {
		Writer out = new StringWriter();
		try {
			mergeTemplate(out);
		    TagRender parent = (TagRender) getParent();
		    if(parent != null) {
		    	parent.body.write(out.toString());
		    }else {
		    	this.pageContext.getOut().write(out.toString());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public int getRequestId() {
        return pageContext.getRequest().hashCode();
    }

    public String getId() {
    	if(StringUtils.isBlank(id)) {
    		int nextInt = new Random().nextInt();
			nextInt = (nextInt == Integer.MIN_VALUE) ? Integer.MAX_VALUE : Math.abs(nextInt);
			this.id = Theme.getTemplateName(getClass()) + nextInt;
    	}
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
	public Object attribute(String name) {
		return pageContext.findAttribute(name);
	}
	
	public Object parameter(String name) {
		return getRequest().getParameter(name);
	}
	
	public String getParams() {
		return BingoFunction.getParamstring(getRequest());
	}
	
	public String getRequestURI() {
		return BingoFunction.transformRequestURI(getRequest());
	}
    
    public HttpServletRequest getRequest() {
    	return (HttpServletRequest) pageContext.getRequest();
    }

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getBody() {
		if(body != null && StringUtils.isNotBlank(body.toString())) {
			return body.toString();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T findParentTag(Class<T> c) {
		TagRender tag = this;
		while(tag.getParent() != null) {
    		tag = (TagRender) tag.getParent();
    		if(tag.getClass().isAssignableFrom(c)) {
    			return (T) tag;
    		}
    	}
		return null;
	}
	
	public Theme getTagTheme() {
		if(StringUtils.isBlank(theme)) {
			TagRender parent = (TagRender) getParent();
			if(parent == null) {
				return new Theme();
			}else {
				return parent.getTagTheme();
			}
		}else {
			return new Theme(theme);
		}
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

}
