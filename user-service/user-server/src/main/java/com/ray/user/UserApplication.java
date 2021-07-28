package com.ray.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 张烈文
 * @date 2021/7/27 14:21
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ray.message","com.ray.sso"})
@EnableDiscoveryClient
@MapperScan("com.ray.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
