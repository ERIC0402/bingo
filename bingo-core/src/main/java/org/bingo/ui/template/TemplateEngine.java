/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.ui.template;

import java.io.Writer;

import org.bingo.ui.TagRender;

public interface TemplateEngine {

	public void render(String templatePath, Writer writer, TagRender tag) throws Exception;

	public String getSuffix();
}
