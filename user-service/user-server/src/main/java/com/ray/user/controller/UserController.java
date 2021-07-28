package com.ray.user.controller;

import com.ray.dto.UserDTO;
import com.ray.entity.User;
import com.ray.user.response.Response;
import com.ray.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 张烈文
 * @date 2021/7/27 16:28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("toLogin")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
    @PostMapping("login")
    public Response login(@RequestParam("name") String name, @RequestParam("password") String password) {
        return userService.login(name, password);
    }

    @GetMapping("sendverycode")
    public Response sendVeryCode(String mobile, String email) {
        return userService.sendMessage(mobile, email);
    }

    @PostMapping("register")
    public Response register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @GetMapping("info")
    public User info(Integer userId) {
        return userService.getStudentInfo(userId);
    }

}
