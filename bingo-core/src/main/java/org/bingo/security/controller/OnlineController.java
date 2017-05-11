package org.bingo.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.bingo.springmvc.controller.BaseCommonController;

@Controller
@RequestMapping("/online")
public class OnlineController extends BaseCommonController {

	@Autowired
	private SessionRegistry sessionRegistry;
	
	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping("/userlist")
	public @ResponseBody List<Object> list() {
		return sessionRegistry.getAllPrincipals();
	}

	/**
	 * 强制让用户下线
	 */
	@RequestMapping("/shotOff")
	public @ResponseBody Map<String, Object> shotOff(String username) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 用户列表
		List<Object> userList = sessionRegistry.getAllPrincipals();
		for (int i = 0; i < userList.size(); i++) {
			User userTemp = (User) userList.get(i);
			if (userTemp.getUsername().equals(username)) {
				List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(userTemp, false);
				if (sessionInformationList != null) {
					for (SessionInformation sis : sessionInformationList) {
						sis.expireNow();
						String remark = userTemp.getUsername() + "被管理员" + username + "踢出";
						System.out.print(remark);

					}
					map.put("status", "y");
					map.put("info", "强制下线成功！");
				} else {
					map.put("status", "n");
					map.put("info", "用户会话信息不存在！");
				}
			} else {
				map.put("status", "n");
				map.put("info", "记录表中不存在当前用户！");
			}
		}

		return map;
	}

}
