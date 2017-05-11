package org.bingo.security.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.bingo.security.model.Resource;
import org.bingo.security.service.ResourceService;
import org.bingo.springmvc.impl.BaseServiceImpl;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {
	
	public List<Resource> findAllResource() {
		return entityDao.getAll(Resource.class);
	}

}
