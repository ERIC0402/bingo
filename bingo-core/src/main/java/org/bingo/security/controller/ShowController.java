package org.bingo.security.controller;

import java.util.List;

import javax.annotation.Resource;

import org.bingo.show.model.Show;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.bingo.common.collection.CollectUtils;
import org.bingo.hibernate.model.query.page.BootstrapPage;
import org.bingo.springmvc.controller.BaseCommonController;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
@RequestMapping("/show")
public class ShowController extends BaseCommonController{
	
	@Resource
    private ImageCaptchaService imageCaptchaService;

	@RequestMapping("/table")
	@ResponseBody
	public BootstrapPage<Show> table() {
		Integer total = 2;
		List<Show> shows = CollectUtils.newArrayList();
		shows.add(new Show(1L, "张三", 10, "牛逼", "牛人"));
		shows.add(new Show(2L, "王五", 10, "牛逼", "牛人"));
		return new BootstrapPage<Show>(total, shows);
	}
	
}
