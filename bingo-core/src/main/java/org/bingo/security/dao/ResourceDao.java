package org.bingo.security.dao;

import java.util.Map;

public interface ResourceDao {
	/**
	 * 获取资源权限列表
	 * @return
	 */
	Map<String,String> getResources();
}
