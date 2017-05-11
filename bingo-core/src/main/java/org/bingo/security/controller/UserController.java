package org.bingo.security.controller;

import java.util.List;

import org.bingo.show.model.Show;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.bingo.common.collection.CollectUtils;
import org.bingo.hibernate.model.query.page.SinglePage;
import org.bingo.hibernate.query.QueryBuilder;
import org.bingo.hibernate.query.builder.OqlBuilder;
import org.bingo.security.model.User;
import org.bingo.springmvc.controller.BaseCommonController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseCommonController {
	
	@Override
	protected String getEntityName() {
		return User.class.getName();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	protected QueryBuilder<?> getQueryBuilder() {
		OqlBuilder<User> query = (OqlBuilder<User>) super.getQueryBuilder();
		UserController user = new UserController();
		user.findUser();
		return query;
	}
	
	public void search() {
		getRequest().getRequestURI();
		Integer total = 2;
		List<Show> shows = CollectUtils.newArrayList();
		shows.add(new Show(1L, "张三", 10, "牛逼", "牛人"));
		shows.add(new Show(2L, "王五", 10, "牛逼", "牛人"));
		put("page", new SinglePage<Show>(1, 2, total, shows));
		super.search();
	}

	@Cacheable("builder")
	public User findUser() {
		return new User();
	}
	
}
