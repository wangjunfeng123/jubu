package com.ninep.jubu.interceptor;


import com.ninep.jubu.cache.JubuThreadCache;
import com.ninep.jubu.domain.User;
import com.ninep.jubu.enums.UserWorkStatus;
import com.ninep.jubu.manager.TokenManager;
import com.ninep.jubu.result.ApiResponse;
import com.ninep.jubu.result.ErrorType;
import com.ninep.jubu.service.AuthService;
import com.ninep.jubu.service.UserService;
import com.ninep.jubu.utils.FarmUtils;
import com.ninep.jubu.utils.ObjectUtil;
import com.ninep.jubu.vo.TokenModel;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.ninep.jubu.constant.JubuConstant.AUTHORIZATION;


/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 拦截器 .
 * @since 2018/07/02
 */
@Component
public class WebInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(WebInterceptor.class);

    @Value("${com.environment.debug}")
    private Boolean isDebug;

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        JubuThreadCache.setStartTime();
        String uri = request.getRequestURI();
        if (uri.contains("login")
                || uri.contains("swagger")
                || uri.contains("/test")
                || uri.contains("api")) {
            return true;
        }
        if (uri.startsWith("/static/") || uri.endsWith(".css") || uri.endsWith(".htm")
                || uri.endsWith(".js") || uri.endsWith(".ico") || uri.endsWith(".xml")
                || uri.endsWith(".png") || uri.endsWith("txt")) {
            return true;
        }
        if (uri.equals("/")) {
            response.sendRedirect("login.html");
            return false;
        }

        User user = handleBrowser(request);

        if (isDebug && ObjectUtil.isNullObj(user)) {
            user = FarmUtils.handleDebug();
        }

        if (ObjectUtil.isNullObj(user)) {
            Map<String, Object> data = ApiResponse.createFailMsgResult(ErrorType.LOGIN_INFO_ERROR, "没有检测到用户登录信息");
            FarmUtils.writeJsonResponse(data, response);
            return false;
        }
        JubuThreadCache.setUser(user);

        //todo 处理页面权限  shiro 实现
        //todo 处理接口权限
        return true;
    }

    //检测请求的是否登录
    private User handleBrowser(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String authentication = getAuthentication(request);
        if (StringUtils.isEmpty(authentication)) {
            logger.error("WebInterceptor preHandle uri:{} not login ", uri);
            return null;
        }
        TokenModel tokenModel = tokenManager.getToken(authentication);
        if (!tokenManager.checkToken(tokenModel)) {
            return null;
        }
        User user = userService.getDetail(tokenModel.getUserId());
        if (user == null || !UserWorkStatus.IN_WORK.getIndex().equals(user.getStatus())) {//未登录或已离职
            logger.error("WebInterceptor preHandle uri:{} authentication:{} user not exist ", uri, authentication);
            return null;
        }
        return user;
    }

    //获取本次请求的证书 AUTHORIZATION
    private String getAuthentication(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTHORIZATION)) {
                    return cookie.getValue();
                }
            }
        }
        String authorization = request.getHeader(AUTHORIZATION);
        if (StringUtils.isNotEmpty(authorization)) {
            return authorization;
        }
        return request.getParameter(AUTHORIZATION);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.endsWith(".ajax") && (uri.indexOf('.') != -1 || uri.contains("/cron/"))) {
            return;
        }

        long time = JubuThreadCache.getStartTime();
        if (time > 0) {
            long timeCost = System.currentTimeMillis() - time;
            logger.info("time used, {}, {}ms", request.getRequestURI(), timeCost);
        }

        //清除  thread cache 中的参数
        JubuThreadCache.clear();
    }

}