package org.bingo.security.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.bingo.security.exception.BadCaptchaException;

import com.octo.captcha.service.image.ImageCaptchaService;

public class UsernamePasswordCaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Resource
    private ImageCaptchaService imageCaptchaService;
    
    public final static String LOGIN_FAIL_SIZE = "LOGIN_FAIL_SIZE";
    
    private Integer needCaptchaSize = 3;
    
    @Override  
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
    	Integer loginFailSize = (Integer) request.getSession().getAttribute(LOGIN_FAIL_SIZE);
    	if(loginFailSize != null && loginFailSize > needCaptchaSize) {
    		String captchaId = request.getSession().getId();
            String code = request.getParameter("code");
            try{
            	Boolean isCorrect = imageCaptchaService.validateResponseForID(captchaId, code);
                if (!isCorrect){
                    throw new BadCaptchaException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.errorCaptcha"));
                }
            }catch(Exception ee) {
            	throw new BadCaptchaException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.errorCaptchaValidate"));
            }
    	}
        return super.attemptAuthentication(request, response);
    }

	public void setNeedCaptchaSize(Integer needCaptchaSize) {
		this.needCaptchaSize = needCaptchaSize;
	}

}
