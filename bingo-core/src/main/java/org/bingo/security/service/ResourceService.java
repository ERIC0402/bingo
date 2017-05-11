package org.bingo.security.service;

import java.util.List;

import org.bingo.security.model.Resource;


public interface ResourceService {
	
	/**
	 * 获取所有有效资源
	 * @return
	 */
	public List<Resource> findAllResource();

}
