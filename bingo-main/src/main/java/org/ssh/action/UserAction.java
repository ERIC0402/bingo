package org.ssh.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssh.model.User;
import org.ssh.service.UserService;

/**  
 * 用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAction.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable Long userId){
		LOGGER.info("查询用户：" + userId);
		User userInfo = userService.get(userId);
		modelMap.addAttribute("userInfo", userInfo);
		return "/user/showInfo";
	}
	
	@RequestMapping("/showInfos")
	public @ResponseBody List<User> showUserInfos(){
		LOGGER.info("查询用户全部用户");
		List<User> userInfos = userService.findAll();
		return userInfos;
	}
}
