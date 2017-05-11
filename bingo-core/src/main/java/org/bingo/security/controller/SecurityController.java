package org.bingo.security.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.bingo.springmvc.controller.BaseCommonController;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
@RequestMapping("/security")
public class SecurityController extends BaseCommonController{
	
	@Resource
    private ImageCaptchaService imageCaptchaService;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Welcome - Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;
 
	}
 
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Admin - Spring Security Hello World");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");
 
		return model;
 
	}
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
 
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
 
		return model;
 
	}
	
	@RequestMapping(value = "/index**", method = RequestMethod.GET)
	public ModelAndView indexPage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "User - Home Page");
		model.addObject("message", "This is User access page!");
		model.setViewName("index");
 
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
	
	  
    /** 
     * 指定无访问额权限页面 
     *  
     * @return 
     */  
    @RequestMapping(value = "/denied**", method = RequestMethod.GET)  
    public String deniedPage() {  
  
        logger.debug("Received request to show denied page");  
  
        return "denied";  
  
    }  
 
}
