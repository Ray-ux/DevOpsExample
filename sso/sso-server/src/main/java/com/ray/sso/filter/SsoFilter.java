package com.ray.sso.filter;

import com.ray.sso.jwtutil.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张烈文
 * @date 2021/7/28 13:11
 */
@Slf4j
@Component
public abstract class SsoFilter implements Filter{


        private static final JwtOperator jwtOperator = new JwtOperator();
//    @Resource
//    private JwtOperator jwtOperator;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("X-Token");
        if (null == token) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("X-Token".equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        log.info("token:{}", token);
        if (token == null) {
            log.error("token不存在");
            response.sendRedirect("http://localhost:8080/user/toLogin");
            return;
        }
        Boolean flag = false;
        try {
            log.info("开始校验token");
            flag = jwtOperator.validateToken(token);
        } catch (Exception e) {
            log.error("token已被篡改");
            response.sendRedirect("http://localhost:8080/user/toLogin");
            return;
        }
        if (!flag) {
            log.warn("token已过期");
            response.sendRedirect("http://localhost:8080/user/toLogin");
            return;
        }
        Claims claimsFromToken = jwtOperator.getClaimsFromToken(token);
        Integer userId = (Integer) claimsFromToken.get("userId");
        validateSuccess(request, response, userId);
        filterChain.doFilter(request, response);
    }

    /**
     * 该方法用于继承该类的服务使用：即token校验成功后需要给调用服务提供用户信息
     * @param request
     * @param response
     * @param userId
     */
    protected abstract void validateSuccess(HttpServletRequest request, HttpServletResponse response, Integer userId);
}
