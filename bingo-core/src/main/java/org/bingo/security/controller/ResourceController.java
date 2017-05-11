package org.bingo.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.bingo.hibernate.model.Entity;
import org.bingo.security.impl.FilterInvocationSecurityMetadataSourceImpl;
import org.bingo.security.model.Resource;
import org.bingo.springmvc.controller.BaseCommonController;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseCommonController {
	
	@Autowired
	private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
	
	@Override
	protected String getEntityName() {
		return Resource.class.getName();
	}
	
	@Override
	protected boolean saveOrUpdateAfter(Object obj) {
		return super.saveOrUpdateAfter(obj);
	}
	
}
