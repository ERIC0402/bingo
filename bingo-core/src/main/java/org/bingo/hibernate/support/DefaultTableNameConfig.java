/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.support;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bingo.common.collection.CollectUtils;
import org.bingo.common.util.StrUtils;
import org.bingo.spring.config.ConfigResource;

/**
 * 根据报名动态设置schema,prefix名字
 * 
 * @author chaostone
 */
public class DefaultTableNameConfig implements TableNameConfig {

	// 表名、字段名、序列长度
	private static final int MaxLength = 25;

	private static final Logger logger = LoggerFactory.getLogger(DefaultTableNameConfig.class);

	private final List<TableNamePattern> patterns = new ArrayList<TableNamePattern>();

	private final Map<String, TableNamePattern> packagePatterns = CollectUtils.newHashMap();

	private ConfigResource resource;

	public void addConfig(URL url) {
		loadProperties(url);
		Collections.sort(patterns);
	}

	private void loadProperties(URL url) {
		try {
			logger.debug("loading {}", url);
			InputStream is = url.openStream();
			Properties props = new Properties();
			if (null != is) {
				props.load(is);
			}
			for (Iterator<Object> iter = props.keySet().iterator(); iter.hasNext();) {
				String packageName = (String) iter.next();
				String schemaPrefix = props.getProperty(packageName).trim();

				String schema = null;
				String prefix = null;
				String abbreviationStr = null;
				int commaIndex = schemaPrefix.indexOf(',');
				if (commaIndex < 0 || (commaIndex + 1 == schemaPrefix.length())) {
					schema = schemaPrefix;
				} else if (commaIndex == 0) {
					prefix = schemaPrefix.substring(1);
				} else {
					schema = StringUtils.substringBefore(schemaPrefix, ",");
					prefix = StringUtils.substringAfter(schemaPrefix, ",");
				}
				if (StringUtils.contains(prefix, ",")) {
					abbreviationStr = StringUtils.substringAfter(prefix, ",").toLowerCase();
					prefix = StringUtils.substringBefore(prefix, ",");
				}
				TableNamePattern pattern = (TableNamePattern) packagePatterns.get(packageName);
				if (null == pattern) {
					pattern = new TableNamePattern(packageName, schema, prefix);
					packagePatterns.put(packageName, pattern);
					patterns.add(pattern);
				} else {
					pattern.setSchema(schema);
					pattern.setPrefix(prefix);
				}
				if (null != abbreviationStr) {
					String[] pairs = StringUtils.split(abbreviationStr, ";");
					for (String pair : pairs) {
						String longName = StringUtils.substringBefore(pair, "=");
						String shortName = StringUtils.substringAfter(pair, "=");
						pattern.abbreviations.put(longName, shortName);
					}
				}
			}
			is.close();
		} catch (IOException e) {
			logger.error("property load error", e);
		}
	}

	public String getSchema(String packageName) {
		String schemaName = null;
		for (TableNamePattern packageSchema : patterns) {
			if (packageName.indexOf(packageSchema.getPackageName()) == 0) {
				schemaName = packageSchema.getSchema();
			}
		}
		return schemaName;
	}

	public TableNamePattern getPattern(String packageName) {
		TableNamePattern pattern = null;
		for (TableNamePattern packageSchema : patterns) {
			if (packageName.indexOf(packageSchema.getPackageName()) == 0) {
				pattern = packageSchema;
			}
		}
		return pattern;
	}

	public String getPrefix(String packageName) {
		String prefix = null;
		for (TableNamePattern packageSchema : patterns) {
			if (packageName.indexOf(packageSchema.getPackageName()) == 0) {
				prefix = packageSchema.getPrefix();
			}
		}
		return prefix;
	}

	public List<TableNamePattern> getPatterns() {
		return patterns;
	}

	public ConfigResource getResource() {
		return resource;
	}

	public void setResource(ConfigResource resource) {
		this.resource = resource;
		if (null != resource) {
			for (final URL url : resource.getAllPaths()) {
				addConfig(url);
			}
			if (logger.isInfoEnabled()) {
				logger.info("Table name pattern: -> \n{}", this.toString());
			}
		}
	}

	public String toString() {
		int maxlength = 0;
		for (TableNamePattern pattern : patterns) {
			if (pattern.getPackageName().length() > maxlength) {
				maxlength = pattern.getPackageName().length();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < patterns.size(); i++) {
			TableNamePattern pattern = patterns.get(i);
			sb.append(StringUtils.rightPad(pattern.getPackageName(), maxlength)).append(" : [")
					.append(pattern.getSchema());
			sb.append(" , ").append(pattern.getPrefix()).append(']');
			if (i < patterns.size() - 1) sb.append('\n');
		}
		return sb.toString();
	}

	public String classToTableName(String className) {
		if (className.endsWith("Bean")) {
			className = StringUtils.substringBeforeLast(className, "Bean");
		}
		String tableName = addUnderscores(unqualify(className));
		TableNamePattern pattern = getPattern(className);
		if (null != pattern) {
			tableName = pattern.prefix + tableName;
		}
		if (tableName.length() > MaxLength && null != pattern) {
			for (Map.Entry<String, String> pairEntry : pattern.abbreviations.entrySet()) {
				tableName = StringUtils.replace(tableName, pairEntry.getKey(), pairEntry.getValue());
			}
		}
		return tableName;
	}

	public String collectionToTableName(String className, String tableName, String collectionName) {
		TableNamePattern pattern = getPattern(className);
		String collectionTableName = tableName + "_" + addUnderscores(unqualify(collectionName));
		if (collectionTableName.length() > MaxLength && null != pattern) {
			for (Map.Entry<String, String> pairEntry : pattern.abbreviations.entrySet()) {
				collectionTableName = StringUtils.replace(collectionTableName, pairEntry.getKey(),
						pairEntry.getValue());
			}
		}
		return collectionTableName;
	}

	protected static String unqualify(String qualifiedName) {
		int loc = qualifiedName.lastIndexOf('.');
		return (loc < 0) ? qualifiedName : qualifiedName.substring(loc + 1);
	}

	protected static String addUnderscores(String name) {
		return StrUtils.unCamel(name.replace('.', '_'), '_');
	}


}

/**
 * 表命名模式
 * 
 * @author chaostone
 */
class TableNamePattern implements Comparable<TableNamePattern> {
	// 报名
	String packageName;
	// 模式名
	String schema;
	// 前缀名
	String prefix;

	Map<String, String> abbreviations = CollectUtils.newHashMap();

	public TableNamePattern(String packageName, String schemaName, String prefix) {
		this.packageName = packageName;
		this.schema = schemaName;
		this.prefix = prefix;
	}

	public int compareTo(TableNamePattern other) {
		return this.packageName.compareTo(other.packageName);
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schemaName) {
		this.schema = schemaName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[package:").append(packageName).append(", schema:").append(schema);
		sb.append(", prefix:").append(prefix).append(']');
		sb.append(", abbreviations:").append(abbreviations).append(']');
		return sb.toString();
	}

}
