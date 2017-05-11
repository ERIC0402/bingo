/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.ui.template.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Component;
import org.bingo.common.collection.CollectUtils;
import org.bingo.ui.TagRender;
import org.bingo.ui.template.TemplateEngine;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

@Component
public class FreemarkerTemplateEngine implements TemplateEngine {

	protected Configuration config;

	public void render(String templatePath, Writer writer, TagRender tag) throws Exception {
		@SuppressWarnings("deprecation")
		SimpleHash model = new SimpleHash();
		model.put("tag", tag);
		model.put("base", tag.getRequest().getContextPath());
		try {
			Template template = loadTemplate(templatePath);
			Environment env = template.createProcessingEnvironment(model, writer);
			tag.getRequest().setAttribute(templatePath, env);
			env.process();
		} catch (ParseException pe) {
			throw pe;
		}
	}

	private Template loadTemplate(String templateName) throws Exception {
		Template template = null;
		String curTemplate = templateName;
		while (null == template) {
			try {
				template = getConfig().getTemplate(curTemplate);
			} catch (ParseException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
		return template;
	}

	public final String getSuffix() {
		return ".ftl";
	}

	@SuppressWarnings("deprecation")
	public Configuration getConfig() {
			config = new Configuration();
			config.setTemplateLoader(createTemplateLoader());
			// 关闭按国际化查找模板文件
			config.setLocalizedLookup(false);
		return config;
	}
	
	protected TemplateLoader createTemplateLoader() {
		// construct a FileTemplateLoader for the init-param 'TemplatePath'
		List<TemplateLoader> loaders = CollectUtils.newArrayList();
		loaders.add(new BingoFreemarkerTemplateLoader());
		return new MultiTemplateLoader(loaders.toArray(new TemplateLoader[loaders.size()]));
	}
}
