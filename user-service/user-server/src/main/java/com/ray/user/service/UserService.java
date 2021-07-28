package com.ray.user.service;

import com.ray.dto.UserDTO;
import com.ray.entity.User;
import com.ray.user.response.Response;

/**
 * @author 张烈文
 * @date 2021/7/27 14:46
 */
public interface UserService {

    /**
     * 登陆
     * @param name
     * @param password
     * @return
     */
    Response login(String name, String password);

    /**
     * 注册
     * @param userDTO
     */
    Response register(UserDTO userDTO);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    User getStudentInfo(Integer id);


    /**
     * 发送验证码
     * @param email
     * @param mobile
     * @return
     */
    Response sendMessage(String mobile, String email);


}
