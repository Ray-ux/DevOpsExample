package com.ray.sso;

import com.ray.sso.interceptor.TokenReplayRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 张烈文
 * @date 2021/7/27 17:04
 */
@FeignClient(name = "sso",configuration = TokenReplayRequestInterceptor.class)
@RequestMapping("/sso")
public interface SsoClient {

    /**
     * 生成JWT
     *
     * @return
     */
    @GetMapping("genToken")
    String genToken(@RequestParam("userId") Integer userId);


}
