package com.ray.course;

import com.ray.course.filter.CourseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张烈文
 * @date 2021/7/27 22:17
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ray.user","com.ray.sso"})
@EnableDiscoveryClient
public class CourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Autowired
    CourseFilter courseFilter;
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(courseFilter);
        List<String> urlPatten = new ArrayList<>();
        urlPatten.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatten);
        return filterRegistrationBean;
    }
}
