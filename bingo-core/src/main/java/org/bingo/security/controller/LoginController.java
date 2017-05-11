package org.bingo.security.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.bingo.springmvc.controller.BaseCommonController;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseCommonController{
	
	@Resource
    private ImageCaptchaService imageCaptchaService;

	@RequestMapping
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		if(StringUtils.isBlank(getUsername())) {
			model.setViewName("/login/index");
		}else {
			model.setViewName("redirect:/home");
		}
		return model;
 
	}
	
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void getCode(HttpServletRequest request, HttpServletResponse response) {

        try {
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            String captchaId = request.getSession().getId();
            
            BufferedImage challenge = (BufferedImage) imageCaptchaService.getChallengeForID(captchaId,request.getLocale());
            
            ImageIO.write(challenge, "jpeg", jpegOutputStream);
            byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
            
        } catch (Exception e) {
            logger.error("generate captcha image error " + e.getMessage());
        }
        
    }
 
}
