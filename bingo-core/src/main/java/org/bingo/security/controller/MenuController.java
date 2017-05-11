package org.bingo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.bingo.security.model.Menu;
import org.bingo.springmvc.controller.BaseCommonController;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseCommonController {
	
	@Override
	protected String getEntityName() {
		return Menu.class.getName();
	}
	
}
