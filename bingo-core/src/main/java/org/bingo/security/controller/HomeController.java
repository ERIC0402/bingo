package org.bingo.security.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.bingo.springmvc.controller.BaseCommonController;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseCommonController{
	
	@Resource
    private ImageCaptchaService imageCaptchaService;

	@RequestMapping
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		put("fullname", getCurrentUser().getFullName());
		model.setViewName("/home/home");
		return model;
	}
	
}
