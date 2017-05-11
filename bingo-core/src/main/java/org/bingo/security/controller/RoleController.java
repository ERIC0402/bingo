package org.bingo.security.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.bingo.hibernate.model.Entity;
import org.bingo.security.model.Role;
import org.bingo.springmvc.controller.BaseCommonController;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseCommonController {
	
	@Override
	protected String getEntityName() {
		return Role.class.getName();
	}
	
	@Override
	protected String saveAndForward(Entity<?> entity) {
		Role role = (Role) entity;
		role.setCreator(getCurrentUser());
		return super.saveAndForward(entity);
	}
	
	@RequestMapping("activate")
	public String activate() {
		UserController user = new UserController();
		user.findUser();
		String message = "操作失败！";
		Boolean isActive = getBoolean("isActive");
		Long[] roleIds = getEntityIds(getShortName());
		if(isActive != null && roleIds != null && roleIds.length > 0) {
			List<Role> roles = entityDao.get(Role.class, roleIds);
			for(Role role : roles) {
				role.setEnabled(isActive);
			}
			entityDao.saveOrUpdate(roles);
			if(isActive) {
				message = "激活成功！";
			}else {
				message = "冻结成功！";
			}
		}
		return redirect("list", message);
	}
	
}
