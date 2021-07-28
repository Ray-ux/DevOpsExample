package com.ray.course.filter;

import com.ray.sso.SsoClient;
import com.ray.sso.filter.SsoFilter;
import com.ray.user.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张烈文
 * @date 2021/7/27 23:36
 */

@Slf4j
@Component
public class CourseFilter extends SsoFilter {


    @Override
    protected void validateSuccess(HttpServletRequest request, HttpServletResponse response, Integer userId) {
        request.setAttribute("userId",userId);
    }
}
