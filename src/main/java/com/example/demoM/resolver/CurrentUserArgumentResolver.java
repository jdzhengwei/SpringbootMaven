package com.example.demoM.resolver;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.demoM.util.TokenUtil;
import com.example.demoM.vo.UserInfoVO;

/**
 *  基于Token， 結合＠CurrentUser注解將當前登錄用戶注入為方法參數
 *  
 * @author gexiangping
 *
 */
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtil tokenUtil;
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserInfoVO.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	String token = "";
    	Object header = webRequest.getHeader(tokenHeader);
        if(header!=null&&!("".equals(header))){
        	 token = String.valueOf(header);
    	}else{
    		HttpServletRequest request=(HttpServletRequest)webRequest.getNativeRequest();
    		//从headers中没有获取到token信息，从cookie中获取
        	Cookie[] cookies=request.getCookies();
    		if(cookies!=null){
    			for(int i=0;i<cookies.length;i++){
    				Cookie cookie=cookies[i];
    				if(tokenHeader.equals(cookie.getName())){
    					token=cookie.getValue();
    					break;
    				}
    			}
    		}
    	}
        
        //前端已有filter校驗 token header
        //打印临时解决方案
        return tokenUtil.getUserFromToken(token.equals("")?"printToken":token);
    }

}