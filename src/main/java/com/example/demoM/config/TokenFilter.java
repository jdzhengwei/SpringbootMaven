package com.example.demoM.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demoM.error.ErrorInfo;
import com.example.demoM.util.ApiResult;
import com.example.demoM.util.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 */
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static final String URL_WHITE_KEY = "epro:url_white_key";

    private static final String TOKEN_AUTH_KEY = "epro:url_token_auth_key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String path = request.getPathInfo();
//        System.out.println(path);
//        if (urlWhiteListContainPath(path)) {
//            chain.doFilter(request, response);
//            return;
//        }

        String authToken = getToken(request);
        //权限  token拦截器
//        System.out.println("authToken=" + authToken);
        if (tokenUtil.isTokenValid(authToken)) {
            //异步刷新token失效时间
            tokenUtil.refreshToken(authToken);
        } else if (!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
            returnErrorInfo(response, ErrorInfo.TOKEN_INVALID);
            return;
        }

        chain.doFilter(request, response);
    }

    private void returnErrorInfo(HttpServletResponse httpResponse, ErrorInfo errorInfo) throws JsonProcessingException, IOException {
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        ApiResult<Void> result = ApiResult.errorResult(errorInfo);
        httpResponse.getWriter().write(mapper.writeValueAsString(result));
        httpResponse.getWriter().close();
    }


    //从request中获取token信息
    private String getToken(HttpServletRequest request) {
        String authToken = "";

        //首先从headers中获取token信息
        authToken = request.getHeader(tokenHeader);
        if (authToken != null && !("".equals(authToken))) {
            return authToken;
        }

        //从headers中没有获取到token信息，从cookie中获取
        //防止crsf攻击，token不再放到cookie中
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (int i = 0; i < cookies.length; i++) {
//                Cookie cookie = cookies[i];
//                if (tokenHeader.equals(cookie.getName())) {
//                    authToken = cookie.getValue();
//                    break;
//                }
//            }
//        }

        return authToken;

    }

//    private boolean urlWhiteListContainPath(String path) {
//        List<String> whiteUrlList = redisTemplate.opsForList().range(URL_WHITE_KEY, 0, -1);
//        if (whiteUrlList.isEmpty()) {
//            ListUrlWhiteListResp resp = userInfoServiceBlockingStub.listUrlWhiteList(ListUrlWhiteListReq.newBuilder().build());
//            List<String> urlList = resp.getUrlList();
//            if (urlList.size() > 0) {
//                redisTemplate.opsForList().leftPushAll(URL_WHITE_KEY, urlList);
//            }
//            whiteUrlList.addAll(urlList);
//        }
//        if (whiteUrlList.contains(path)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}