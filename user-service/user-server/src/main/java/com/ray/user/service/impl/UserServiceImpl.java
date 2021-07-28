package com.ray.user.service.impl;

import com.ray.dto.UserDTO;
import com.ray.entity.User;
import com.ray.message.MessageClient;
import com.ray.sso.SsoClient;
import com.ray.user.mapper.UserMapper;
import com.ray.user.response.Response;
import com.ray.user.response.ResponseUtil;
import com.ray.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author 张烈文
 * @date 2021/7/27 14:49
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MessageClient messageClient;

    @Autowired
    private SsoClient ssoClient;

    @Override
    public Response login(String name, String password) {
        HashMap<String, Object> result = new HashMap<>();
        User stuByNameAndPwd = userMapper.findStuByNameAndPwd(name, password);
        if (stuByNameAndPwd != null) {
            String token = ssoClient.genToken(stuByNameAndPwd.getId());
            HashMap<String, Object> map = new HashMap<>();
            map.put("X-token", token);
            return ResponseUtil.success(map);
        }
        return ResponseUtil.error("没有该用户，请注册！");
    }

    @Override
    public Response register(UserDTO userDTO) {
        String veryCode = userDTO.getCode();
        String code = null;
        if (!StringUtils.isEmpty(userDTO.getEmail())) {
            code = (String) redisTemplate.opsForValue().get(userDTO.getEmail());
        }else{
            code = (String) redisTemplate.opsForValue().get(userDTO.getMobile());
        }
        if (code == null || !code.equals(veryCode)) {
            log.error("注册失败，请输入正确的验证码！");
            return ResponseUtil.error("请输入正确的验证码");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userMapper.insertStu(user);
        return ResponseUtil.success();
    }

    @Override
    public User getStudentInfo(Integer id) {
        return userMapper.findStuById(id);
    }

    @Override
    public Response sendMessage(String mobile, String email) {
        if (StringUtils.isEmpty(email) && StringUtils.isEmpty(mobile)) {
            return ResponseUtil.error("email and mobile 不能同时为空");
        }
        Boolean result = messageClient.sendMessage(mobile, email);
        if (result) {
            return ResponseUtil.success();
        }
        return ResponseUtil.error("请重试");
    }


}
