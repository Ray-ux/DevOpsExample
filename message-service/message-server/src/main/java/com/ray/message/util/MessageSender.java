package com.ray.message.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 张烈文
 * @date 2021/7/26 23:40
 */
@Slf4j
@Component
public class MessageSender {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送邮件并存到redis中去
     * @param email
     * @return
     */
    public Boolean sendEmail(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("验证码:");
            String code = genVeryCode("0123456789", 6);
            setCodeInRedis(email, code);
            message.setText(code);
            message.setTo(email);
            message.setFrom(javaMailSender.getUsername());
            javaMailSender.send(message);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("message-service  -->  message-server  -->  MessageSender 邮件发送失败");
            return false;
        }
        return true;
    }

    /**
     * 将验证码存入redis
     * 有效时长：1分钟
     * @param connect
     * @param code
     */
    private void setCodeInRedis(String connect, String code) {
        redisTemplate.opsForValue().set(connect, code, 3600, TimeUnit.SECONDS);
    }

    /**
     * 生成六位验证码
     * @param s
     * @param size
     * @return
     */
    private String genVeryCode(String s, int size) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    /**
     * 这里没有接入短信服务，只是简单打印了一下
     * 可考虑引入阿里云短信服务
     * @param mobile
     */
    public Boolean sendMobile(String mobile) {
        String genVeryCode = genVeryCode("0123456789", 6);
        setCodeInRedis(mobile, genVeryCode);
        log.info("sendMobile Code:{}", genVeryCode);
        return true;
    }
}
