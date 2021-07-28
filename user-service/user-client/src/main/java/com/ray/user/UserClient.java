package com.ray.user;

import com.ray.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 张烈文
 * @date 2021/7/27 13:52
 */
@FeignClient("user-service")
public interface UserClient {


    /**
     * 获取指定用户信息
     * @param userId
     * @return
     */
    @GetMapping("user/info")
    User getUserInfo(Integer userId);

}
