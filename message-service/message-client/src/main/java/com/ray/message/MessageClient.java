package com.ray.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 张烈文
 * @date 2021/7/26 22:22
 */
@FeignClient("message-service")
@RequestMapping("message")
public interface MessageClient {

    /**
     * 发送邮件or短息
     * @param mobile 可选
     * @param email 可选
     * @return
     */
    @GetMapping("/sendMessage")
    Boolean sendMessage(@RequestParam(value = "mobile", required = false) String mobile, @RequestParam(value = "email", required = false) String email);

}
