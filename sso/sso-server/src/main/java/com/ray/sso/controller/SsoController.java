package com.ray.sso.controller;

import com.ray.sso.jwtutil.JwtOperator;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 张烈文
 * @date 2021/7/27 17:44
 */
@RestController
@RequestMapping("sso")
public class SsoController {

    @Autowired
    private JwtOperator jwtOperator;

    @GetMapping("genToken")
    public String genToken(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return jwtOperator.generateToken(map);
    }

}
